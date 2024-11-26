plugins {
    java
    alias(libs.plugins.shadow)
    alias(libs.plugins.run.paper)
    alias(libs.plugins.plugin.yml)
}

group = project.properties["group"] as String
version = project.properties["version"] as String
val projectName = project.rootProject.name

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://repo.incendo.org/content/repositories/snapshots")

}

dependencies {
    compileOnly(libs.paper.api)

    paperLibrary(libs.bundles.commands)

    paperLibrary(libs.guice)
    paperLibrary(libs.configurate)
}

val targetJavaVersion = 21

tasks {
    compileJava {
        this.options.encoding = Charsets.UTF_8.name()
        this.options.release.set(targetJavaVersion)
    }
    shadowJar {}
    build {
        dependsOn(shadowJar)
    }

    runServer {
        minecraftVersion("1.21.1")
        downloadPlugins {
//            url("https://download.luckperms.net/1542/bukkit/loader/LuckPerms-Bukkit-5.4.129.jar")
        }
    }
}

paper {
    name = projectName
    authors = listOf("Nikomaru") //TODO: Change this
    website = "https://www.morino.party" //TODO: Change this
    apiVersion = "1.21"
    description = "A simple plugin template for PaperMC" //TODO: Change this
    version = "versionPlaceholder" //Don't change this
    foliaSupported = false
    generateLibrariesJson = true

    main = "${group}.${projectName.lowercase()}.PaperPluginTemplate" //TODO: Change this
    loader = "${group}.${projectName.lowercase()}.PaperPluginTemplateLoader" //TODO: Change this
    bootstrapper = "${group}.${projectName.lowercase()}.PaperPluginTemplateBootstrapper" //TODO: Change this

    serverDependencies {
//        "LuckPerms" {
//            load = "BEFORE"
//        }
    }
}

