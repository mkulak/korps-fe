import kotlinx.css.margin
import kotlinx.html.P
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.onMouseOverFunction
import react.*
import react.dom.p
import styled.css
import styled.styledP
import kotlin.browser.window

class VideoList : RComponent<VideoListProps, RState>() {
    override fun RBuilder.render() {
        for (video in props.videos) {
            styledP {
                css {
                    margin = "20px"
                }
                key = video.id.toString()
                attrs {
                    onClickFunction = {
//                    onMouseOverFunction = {
                        props.onSelectVideo(video.id)
                    }
                }
                if (video.id == props.selectedVideoId) {
                    +"▶ "
                }
                +"${video.speaker}: ${video.title}"
            }
        }
    }
}

external interface VideoListProps : RProps {
    var videos: List<Video>
    var selectedVideoId: Int?
    var onSelectVideo: (Int) -> Unit
}

fun RBuilder.videoList(handler: VideoListProps.() -> Unit): ReactElement =
    child(VideoList::class) {
        attrs(handler)
    }
