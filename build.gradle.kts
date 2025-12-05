plugins {
    kotlin("jvm") version "2.3.0-RC2"
    application
}

kotlin {
    jvmToolchain(25)
}

application {
    val day = findProperty("day") ?: "01"
    println("Running day $day")
    mainClass.set("Day${day}Kt")
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
}

tasks {
    wrapper {
        gradleVersion = "9.2.1"
    }
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
}
