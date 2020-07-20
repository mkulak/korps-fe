import react.*
import react.dom.h1
import react.dom.h3

class App : RComponent<RProps, AppState>() {
    override fun AppState.init() {
        val list = listOf(
            Video(1, "Building and breaking things", "John Doe", "https://youtu.be/PsaFVLr8t4E", false),
            Video(2, "The development process", "Jane Smith", "https://youtu.be/PsaFVLr8t4E", false),
            Video(3, "The Web 7.0", "Matt Miller", "https://youtu.be/PsaFVLr8t4E", false),
            Video(4, "Mouseless development", "Tom Jerry", "https://youtu.be/PsaFVLr8t4E", true)
        )
        videos = LinkedHashMap(list.associateBy { it.id })
    }

    override fun RBuilder.render() {
        h1 {
            +"KotlinConf Explorer"
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
