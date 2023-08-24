pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://www.jitpack.io")
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://www.jitpack.io")

    }
}

rootProject.name = "MovieUpdate"
include(":app")
 