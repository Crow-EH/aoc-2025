plugins {
  kotlin("jvm") version "2.3.0-RC2"
  application
  id("com.diffplug.spotless") version "8.1.0"
}

kotlin { jvmToolchain(25) }

val day = findProperty("day") ?: "01"

application { mainClass.set("Day${day}Kt") }

sourceSets { main { kotlin.srcDir("src") } }

tasks {
  wrapper { gradleVersion = "9.2.1" }
  this.getByName("run") {
    val text = "Running day $day"
    doFirst { println(text) }
  }
}

dependencies { implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2") }

spotless { kotlin { ktfmt() } }
