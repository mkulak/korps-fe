import kotlinx.browser.window
import kotlinx.coroutines.*
import kotlinx.html.js.onClickFunction
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import react.*
import react.dom.button
import react.dom.h1
import react.dom.h3

val App = functionalComponent<RProps> {
    val (state, setState) = useState(AppState(null, emptyMap()))

    useEffect(emptyList()) {
        MainScope().launch {
            val videos = fetchVideos().associateBy { it.id }
            setState(AppState(null, videos))
        }
    }

    fun setSelectedVideo(id: Int) {
        setState(state.copy(currentVideoId = id))
    }

    fun onWatch(video: Video) {
        val newVideos = state.videos + (video.id to video.copy(watched = !video.watched))
        setState(state.copy(videos = newVideos))
    }

    h1 {
        +"KotlinConf Explorer"
    }
    child(counter) {}
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

data class AppState(
    val currentVideoId: Int?,
    val videos: Map<Int, Video>
)


suspend fun fetchVideo(id: Int): Video {
    val str = window.fetch("https://my-json-server.typicode.com/kotlin-hands-on/kotlinconf-json/videos/$id")
        .await()
        .text()
        .await()
    return Json.decodeFromString(str)
}

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