import kotlinx.css.*
import react.dom.*
import styled.StyledComponents
import styled.StyledComponents.css
import styled.css
import styled.injectGlobal
import styled.styledDiv
import kotlin.browser.document

fun main() {
    val styles = CSSBuilder().apply {
        body {
            margin(0.px)
            padding(20.px)
            fontSize = 20.px
            fontFamily = "Open Sans"
        }
    }
    StyledComponents.injectGlobal(styles.toString())
    render(document.getElementById("root")) {
        child(App::class) {}
    }
}

data class Video(val id: Int, val title: String, val speaker: String, val videoUrl: String)

val unwatchedVideos = listOf(
    Video(1, "Building and breaking things", "John Doe", "https://youtu.be/PsaFVLr8t4E"),
    Video(2, "The development process", "Jane Smith", "https://youtu.be/PsaFVLr8t4E"),
    Video(3, "The Web 7.0", "Matt Miller", "https://youtu.be/PsaFVLr8t4E")
)

val watchedVideos = listOf(
    Video(4, "Mouseless development", "Tom Jerry", "https://youtu.be/PsaFVLr8t4E")
)