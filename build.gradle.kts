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
}

modSettings {
    modId(base.archivesName.get())
    modName("Voided Utils")

    entrypoint("main", "org.teamvoided.voided_utils.VoidedUtils::commonInit")
    entrypoint("client", "org.teamvoided.voided_utils.VoidedUtils::clientInit")
    entrypoint("fabric-datagen", "org.teamvoided.voided_utils.VoidedUtils::onInitializeDataGenerator")

    mutation {
        custom = null
    }
}


loom {
    runs {
        create("DataGen") {
            client()
            ideConfigGenerated(true)
            vmArg("-Dfabric-api.datagen")
            vmArg("-Dfabric-api.datagen.output-dir=${file("src/main/generated")}")
            vmArg("-Dfabric-api.datagen.modid=${"voided_utils"}")
            runDir("build/datagen")
        }
        create("data") {
            client()
            configName = "Custom Data"
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