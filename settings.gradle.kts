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

rootProject.name = "MultimodulePractice"
include(":app")
include(":common")
include(":features:auth:api")
include(":features:auth:impl")
include(":features:main:api")
include(":features:main:impl")
include(":features:geo")
