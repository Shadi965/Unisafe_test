pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Unisafe Test"
include(":app")
include(":features:auth")
include(":features:shopping_lists")
include(":features:products")
include(":data:retrofit")
include(":data:common")
include(":data:room")
include(":data:common_impl")
