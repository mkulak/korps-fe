import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.h3
import react.dom.img
import styled.css
import styled.styledButton
import styled.styledDiv

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
            styledButton {
                css {
                    padding = "10px"
                    marginTop = 10.px
                    marginBottom = 10.px
                    display = Display.block
                    backgroundColor = if (props.video.watched) Color.red else Color.lightGreen
                }
                attrs {
                    onClickFunction = {
                        props.onWatchedButtonPressed(props.video)
                    }
                }
                if (props.video.watched) {
                    +"Mark as unwatched"
                } else {
                    +"Mark as watched"
                }
            }
            reactPlayer {
                attrs.url = props.video.videoUrl
            }
        }
    }
}

fun RBuilder.videoPlayer(handler: VideoPlayerProps.() -> Unit): ReactElement {
    return child(VideoPlayer::class) {
        this.attrs(handler)
    }
}

external interface VideoPlayerProps : RProps {
    var video: Video
    var onWatchedButtonPressed: (Video) -> Unit
}
