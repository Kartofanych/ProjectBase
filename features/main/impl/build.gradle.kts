plugins {
    id("com.android.library")
    id("kotlin-android")
    id("org.jetbrains.kotlin.plugin.serialization")
    kotlin("kapt")
    `android-config`
}

android {
    namespace = ProjectConfig.namespace("main.impl")

    buildFeatures.viewBinding = true
    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompiler
    }
}

dependencies {
    api(project(":features:main:api"))
    api(project(":features:main:common"))
    api(project(":features:guide:api"))
    api(project(":features:auth:api"))
    api(project(":features:app_config:api"))
    api(project(":features:filters:api"))
    api(project(":features:splash:api"))
    implementation(project(":features:main_map:impl"))
    implementation(project(":features:main_search:impl"))
    implementation(project(":features:main_favourites:impl"))
    implementation(project(":features:geo"))
    implementation(project(":common"))

    implementation(libs.core.ktx)
    implementation(libs.bundles.compose)

    implementation(libs.bundles.navigation)

    implementation(libs.bundles.datastore)
    implementation(libs.json)

    implementation(libs.maps.mobile)

    implementation(libs.bundles.dagger)
    kapt(libs.bundles.daggerCompiler)

    implementation(libs.bundles.network)
}