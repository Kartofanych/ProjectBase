plugins {
    id("com.android.library")
    id("kotlin-android")
    id("org.jetbrains.kotlin.plugin.serialization")
    kotlin("kapt")
    `android-config`
}

android {
    namespace = ProjectConfig.namespace("favourites.impl")

    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompiler
    }
}

dependencies {
    api(project(":features:main:common"))
    api(project(":features:auth:api"))
    api(project(":features:main_favourites:api"))
    implementation(project(":common"))

    implementation(libs.core.ktx)
    implementation(libs.bundles.compose)

    implementation(libs.bundles.dagger)
    kapt(libs.bundles.daggerCompiler)

    //implementation("dev.chrisbanes.haze:haze:0.7.3")

    implementation(libs.bundles.network)
}