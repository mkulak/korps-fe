import kotlinx.browser.window
import kotlinx.coroutines.*
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.button
import react.dom.h1
import react.dom.h3

class App : RComponent<RProps, AppState>() {
    override fun AppState.init() {
        videos = LinkedHashMap()
        MainScope().launch {
            val list = fetchVideos()
            setState {
                videos = LinkedHashMap(list.associateBy { it.id })
            }
        }
    }

    override fun RBuilder.render() {
        h1 {
            +"KotlinConf Explorer"
        }
        child(counter) {

        }
        h3 {
            +"Videos to watch"
        }
        val (watched, unwatched) = state.videos.values.partition { it.watched }
        videoList {
            videos = unwatched
            selectedVideoId = state.currentVideoId
            onSelectVideo = ::setSelectedVideo
        }
        h3 {
            +"Videos watched"
        }
        videoList {
            videos = watched
            selectedVideoId = state.currentVideoId
            onSelectVideo = ::setSelectedVideo
        }
        state.currentVideoId?.let { current ->
            videoPlayer {
                video = state.videos[current]!!
                onWatchedButtonPressed = ::onWatch
            }
        }
    }

    fun onWatch(video: Video) {
        setState {
            videos.remove(video.id)
            videos[video.id] = video.copy(watched = !video.watched)
        }
    }
    fun setSelectedVideo(videoId: Int) {
        setState {
            currentVideoId = videoId
        }
    }

}

external interface AppState : RState {
    var currentVideoId: Int?
    var videos: LinkedHashMap<Int, Video>
}


suspend fun fetchVideo(id: Int): Video =
    window.fetch("https://my-json-server.typicode.com/kotlin-hands-on/kotlinconf-json/videos/$id")
        .await()
        .json()
        .await()
        .apply { asDynamic().watched = false }
        .unsafeCast<Video>()

suspend fun fetchVideos(): List<Video> = coroutineScope {
    (1..25).map { id ->
        async {
            fetchVideo(id)
        }
    }.awaitAll()
}

val counter = functionalComponent<RProps> {
    val (count, setCount) = useState(0)
    button {
        attrs.onClickFunction = { setCount(count + 1) }
        +count.toString()
    }
}