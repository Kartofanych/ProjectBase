plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
    `android-config`
}

android {
    namespace = ProjectConfig.namespace("search_screen.impl")

    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompiler
    }
}

dependencies {
    api(project(":features:search_screen:api"))
    api(project(":features:search_filters:api"))
    api(project(":features:splash:api"))
    implementation(project(":features:geo"))
    implementation(project(":common"))
    implementation(libs.core.ktx)

    implementation(libs.bundles.compose)

    implementation(libs.bundles.dagger)
    kapt(libs.bundles.daggerCompiler)

    implementation(libs.auth.sdk)

    implementation(libs.bundles.network)
}