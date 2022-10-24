pluginManagement {
    includeBuild("build-logic")
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
rootProject.name = "BlinqChallenge"
include(":app")
include(":core:designsystem")
include(":core:datastore")
include(":core:common")
include(":core:network")
include(":core:data")
include(":core:model")
include(":core:domain")
include(":feature:invite")
include(":core:testing")
include(":feature:home")
include(":feature:congrats")
