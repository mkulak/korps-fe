import kotlinx.css.*
import react.*
import react.dom.h3
import react.dom.img
import styled.css
import styled.styledDiv

external interface VideoPlayerProps : RProps {
    var video: Video
}

class VideoPlayer : RComponent<VideoPlayerProps, RState>() {
    override fun RBuilder.render() {
        styledDiv {
            css {
                position = Position.absolute
                top = 20.px
                right = 20.px
            }
            h3 {
                +"${props.video.speaker}: ${props.video.title}"
            }
            img {
                attrs {
                    src = "https://via.placeholder.com/640x360.png?text=Video+Player+Placeholder"
                }
            }
        }
    }
}

fun RBuilder.videoPlayer(handler: VideoPlayerProps.() -> Unit): ReactElement {
    return child(VideoPlayer::class) {
        this.attrs(handler)
    }
}