plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
    `android-config`
}

android {
    namespace = ProjectConfig.namespace("guide.impl")

    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompiler
    }
}

dependencies {
    api(project(":features:guide:api"))
    api(project(":features:main:api"))
    implementation(project(":common"))
    implementation(libs.core.ktx)

    implementation(libs.bundles.compose)

    implementation(libs.bundles.dagger)
    kapt(libs.bundles.daggerCompiler)

    implementation(libs.auth.sdk)

    implementation(libs.bundles.network)
}