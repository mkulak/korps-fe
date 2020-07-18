import kotlinx.css.*
import react.*
import react.dom.h1
import react.dom.h3
import styled.StyledComponents
import styled.StyledComponents.css
import styled.injectGlobal

class App : RComponent<RProps, AppState>() {
    override fun RBuilder.render() {
        h1 {
            +"KotlinConf Explorer"
        }
        h3 {
            +"Videos to watch"
        }
        videoList {
            videos = unwatchedVideos
            selectedVideo = state.currentVideo
            onSelectVideo = ::setSelectedVideo
        }
        h3 {
            +"Videos watched"
        }
        videoList {
            videos = watchedVideos
            selectedVideo = state.currentVideo
            onSelectVideo = ::setSelectedVideo
        }
        state.currentVideo?.let { currentVideo ->
            videoPlayer {
                video = currentVideo
            }
        }
    }

    fun setSelectedVideo(video: Video) {
        setState {
            currentVideo = video
        }
    }
}

external interface AppState : RState {
    var currentVideo: Video?
}
