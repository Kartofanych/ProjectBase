plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
    `android-config`
}

android {
    namespace = ProjectConfig.namespace("onboarding.impl")

    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompiler
    }
}

dependencies {
    api(project(":features:onboarding:api"))
    implementation(project(":common"))
    implementation(libs.core.ktx)

    implementation(libs.bundles.compose)

    implementation(libs.bundles.dagger)
    kapt(libs.bundles.daggerCompiler)

    implementation(libs.auth.sdk)

    implementation(libs.bundles.network)
}