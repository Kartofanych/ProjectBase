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
include(":features:main:common")
include(":features:main_map:api")
include(":features:main_map:impl")
include(":features:main_search:api")
include(":features:main_search:impl")
include(":features:main_favourites:api")
include(":features:main_favourites:impl")
include(":features:geo")
include(":features:landmark")
include(":features:login:api")
include(":features:login:impl")
include(":features:guide:api")
include(":features:guide:impl")
include(":features:audio_guide:api")
include(":features:audio_guide:impl")
include(":features:app_config:api")
include(":features:app_config:impl")
include(":features:filters:api")
include(":features:filters:impl")
include(":features:splash:api")
include(":features:splash:impl")
include(":features:service:api")
include(":features:service:impl")
