import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.0"
    kotlin("plugin.serialization") version "1.9.0"
    id("org.teamvoided.iridium") version "2.2.3"
    id("iridium.mod.build-script") version "2.2.3"
}

group = project.properties["maven_group"]!!
version = project.properties["mod_version"]!!
base.archivesName.set(project.properties["archives_base_name"] as String)
description = "Many QoL features\nMany Util features"

repositories {
    mavenCentral()
    maven {
        name = "TerraformersMC"
        url = uri("https://maven.terraformersmc.com/")
    }
}

dependencies {
    modImplementation("com.llamalad7.mixinextras:mixinextras-fabric:${project.properties["mixin_extras_version"]}")?.let { include(it) }
    annotationProcessor("com.llamalad7.mixinextras:mixinextras-fabric:${project.properties["mixin_extras_version"]}")
    modImplementation(files("scuffedlib-1.0.0+1.20.1.jar"))
}

modSettings {
    modId(base.archivesName.get())
    modName("Voided Utils")

    entrypoint("main", "org.teamvoided.voided_utils.VoidedUtils::commonInit")
    entrypoint("client", "org.teamvoided.voided_utils.VoidedUtils::clientInit")
    entrypoint("fabric-datagen", "org.teamvoided.voided_utils.VoidedUtilsData")
    mixinFile("voided_utils.mixins.json")

    isModParent(true)
}


loom {
    runs {
        create("data") {
            client()
            ideConfigGenerated(true)
            vmArg("-Dfabric-api.datagen")
            vmArg("-Dfabric-api.datagen.output-dir=${file("src/main/generated")}")
            vmArg("-Dfabric-api.datagen.modid=${"voided_utils"}")
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