import kotlinx.css.*
import kotlinx.html.js.onClickFunction
import react.*
import react.dom.h3
import react.dom.img
import styled.css
import styled.styledButton
import styled.styledDiv

val VideoPlayer = functionalComponent<VideoPlayerProps> { props ->
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
            val title = if (props.video.watched) "Mark as unwatched" else "Mark as watched"
            +title
        }
        styledDiv {
            css {
                display = Display.flex
                marginBottom = 10.px
            }
            emailShareButton {
                attrs.url = props.video.videoUrl
                emailIcon {
                    attrs.size = 32
                    attrs.round = true
                }
            }
            telegramShareButton {
                attrs.url = props.video.videoUrl
                telegramIcon {
                    attrs.size = 32
                    attrs.round = true
                }
            }
        }
        reactPlayer {
            attrs.url = props.video.videoUrl
        }
    }
}

fun RBuilder.videoPlayer(handler: VideoPlayerProps.() -> Unit): ReactElement =
    child(VideoPlayer) {
        attrs(handler)
    }

external interface VideoPlayerProps : RProps {
    var video: Video
    var onWatchedButtonPressed: (Video) -> Unit
}
