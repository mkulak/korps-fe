import kotlinx.browser.document
import kotlinx.css.*
import react.dom.render
import styled.injectGlobal

fun main() {
    injectGlobal {
        body {
            margin(0.px)
            padding(20.px)
            fontSize = 20.px
            fontFamily = "Open Sans"
//            backgroundColor = Color.red
        }
    }

    render(document.getElementById("root")) {
        child(App::class) {}
    }
}

data class Video(val id: Int, val title: String, val speaker: String, val videoUrl: String, var watched: Boolean)