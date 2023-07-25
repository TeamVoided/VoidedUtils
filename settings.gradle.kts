pluginManagement {
    repositories {
        maven("https://maven.fabricmc.net/") {
            name = "Fabric"
        }
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.teamvoided.org/releases")
    }

    plugins {
        id("fabric-loom") version "1.2-SNAPSHOT"
    }
}

rootProject.name = "VoidedUtils"

