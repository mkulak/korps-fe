val kotlinVersion = "1.4.10"

plugins {
    kotlin("js") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.10"
}

repositories {
    maven("https://kotlin.bintray.com/kotlin-js-wrappers/")
    mavenCentral()
    jcenter()
}

kotlin {
//    js(IR) {
    js {
        browser()
        binaries.executable()
    }
}

dependencies {
    implementation("org.jetbrains:kotlin-react:16.13.1-pre.116-kotlin-$kotlinVersion")
    implementation("org.jetbrains:kotlin-react-dom:16.13.1-pre.116-kotlin-$kotlinVersion")
    implementation("org.jetbrains:kotlin-styled:5.2.0-pre.116-kotlin-$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.3.8")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-js:1.0.0-RC2")
    implementation(npm("react", "16.13.1"))
    implementation(npm("react-dom", "16.13.1"))
    implementation(npm("react-is", "16.13.1"))
    implementation(npm("styled-components", "5.2.0"))
    implementation(npm("inline-style-prefixer", "6.0.0"))
    implementation(npm("react-player", "2.6.2"))
    implementation(npm("react-share", "4.2.1"))
}