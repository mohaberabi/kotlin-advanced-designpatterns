import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "2.0.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation("io.arrow-kt:arrow-core:1.2.4")
    implementation("io.arrow-kt:arrow-fx-coroutines:1.2.4")
    implementation("io.arrow-kt:arrow-fx-stm-jvm:1.2.1")
    implementation("io.arrow-kt:arrow-resilience-jvm:1.2.1")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(18)
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.freeCompilerArgs += "-Xcontext-receivers"
}