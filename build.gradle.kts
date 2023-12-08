import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("fabric-loom") version "1.3.8"
    kotlin("jvm") version "1.9.10"
    kotlin("plugin.serialization") version "1.9.10"
    id("org.teamvoided.iridium") version "3.1.9"
}

group = project.properties["maven_group"]!!
version = project.properties["mod_version"]!!
base.archivesName.set(project.properties["archives_base_name"] as String)
description = "Many QoL features\nMany Util features"

repositories {
    mavenCentral()
    maven("https://maven.terraformersmc.com/") {
        name = "TerraformersMC"
    }
    maven("https://maven.teamvoided.org/releases") {
        name = "brokenfuseReleases"
    }

}

modSettings {
    modId("voided_utils")
    modName("Voided Utils")

    entrypoint("main", "org.teamvoided.voided_utils.VoidedUtils::commonInit")
    entrypoint("client", "org.teamvoided.voided_utils.VoidedUtils::clientInit")
    entrypoint("fabric-datagen", "org.teamvoided.voided_utils.VoidedUtilsData")
    mixinFile("voided_utils.mixins.json")
    accessWidener("voided_utils.accesswidener")

}

dependencies {
    modImplementation("com.llamalad7.mixinextras:mixinextras-fabric:${project.properties["mixin_extras_version"]}")?.let {
        include(it)
    }
    annotationProcessor("com.llamalad7.mixinextras:mixinextras-fabric:${project.properties["mixin_extras_version"]}")
    modImplementation(files("scuffedlib-1.0.0+1.20.1.jar"))

    modImplementation("org.teamvoided:voidlib-core:1.5.8+1.20.1-SNAPSHOT")
    modImplementation("org.teamvoided:voidlib-vui:1.5.8+1.20.1-SNAPSHOT")
    modImplementation("org.teamvoided:voidlib-config:1.5.8+1.20.1-SNAPSHOT")
}


loom {
    accessWidenerPath.set(file("src/main/resources/voided_utils.accesswidener"))
    runs {
        create("data") {
            client()
            ideConfigGenerated(true)
            vmArg("-Dfabric-api.datagen")
            vmArg("-Dfabric-api.datagen.output-dir=${file("src/main/generated")}")
            vmArg("-Dfabric-api.datagen.modid=${modSettings.modId()}")
            runDir("build/datagen")
        }
    }
}
tasks {
    val targetJavaVersion = 17
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(targetJavaVersion)
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = targetJavaVersion.toString()
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(JavaVersion.toVersion(targetJavaVersion).toString()))
        withSourcesJar()
    }

}

sourceSets["main"].resources.srcDir("src/main/generated")