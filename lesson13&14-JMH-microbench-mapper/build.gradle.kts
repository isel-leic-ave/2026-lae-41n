plugins {
    alias(libs.plugins.kotlin.jvm)
    id("me.champeau.jmh") version "0.7.3"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("reflect"))
//    implementation(project(":lesson12-domain-model"))
//    implementation(project(":lesson15-naive-mapper-via-constructor"))
    //implementation(project(":lesson10-annotations"))

    testImplementation(kotlin("test"))
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}
