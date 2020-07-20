import kotlinx.css.*
import react.dom.render
import styled.StyledComponents
import styled.injectGlobal
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

data class Video(val id: Int, val title: String, val speaker: String, val videoUrl: String, val watched: Boolean)
