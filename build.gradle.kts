plugins {
    java
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io/")
}

dependencies {
    compileOnly("com.github.react-minecraft.plugin:react-api:${property("reactVersion")}")
    compileOnly("com.github.react-minecraft.plugin:react-common:${property("reactVersion")}")
    compileOnly("com.github.react-minecraft:rcp:${property("inferenceApiVersion")}")
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
    compileOnly("com.github.g4vrk.TextSerializer:legacy:1.0.0")
}

tasks {
    withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        options.release.set(17)
    }

    processResources {
        val properties = mapOf(
            "name" to rootProject.name,
            "version" to project.version.toString(),
            "description" to project.description.toString()
        )
        inputs.properties(properties)
        filesMatching("react-addon.yml") {
            expand(properties)
        }
    }
}
