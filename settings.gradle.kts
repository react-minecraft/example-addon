rootProject.name = "example-addon"

// Builds against a local plugin checkout instead of jitpack when it sits next to this repository.
val localPlugin = file("../react-vector")
if (System.getenv("CI") == null && localPlugin.resolve("common").isDirectory) {
    includeBuild(localPlugin) {
        dependencySubstitution {
            substitute(module("com.github.react-minecraft.plugin:react-api")).using(project(":api"))
            substitute(module("com.github.react-minecraft.plugin:react-common")).using(project(":common"))
        }
    }
}
