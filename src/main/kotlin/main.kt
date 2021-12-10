import kotlinx.browser.document
import kotlinx.browser.window
import org.w3c.dom.HTMLDivElement
import kotlinx.browser.*
import kotlinx.html.*
import kotlinx.html.dom.*

fun main() {
//    window.onload = { event ->
//        val root = document.getElementById("root") as HTMLDivElement
////        root.innerHTML = h1 {
////            "<h1>Hello korps-fe</h1>"
////        }
//        Unit
//    }
    document.body!!.append.div {
        h1 {
            +"Welcome to Kotlin"
        }
    }
}
