import kotlinx.css.*
import react.*
import react.dom.*
import styled.css
import styled.styledDiv

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
        styledDiv {
            css {
                position = Position.absolute
                top = 10.px
                right = 10.px
            }
            h3 {
                +"John Doe: Building and breaking things"
            }
            img {
                attrs {
                    src = "https://via.placeholder.com/640x360.png?text=Video+Player+Placeholder"
                }
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
