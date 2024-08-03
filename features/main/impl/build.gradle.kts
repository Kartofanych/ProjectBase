plugins {
    id("com.android.library")
    id("kotlin-android")
    id("org.jetbrains.kotlin.plugin.serialization")
    kotlin("kapt")
    // Precompiled plugin with the base android configuration.
    // Declared in buildSrc/.../android-config.gradle.kts.
    `android-config`
}

android {
    namespace = ProjectConfig.namespace("main.impl")

    buildFeatures.viewBinding = true
    // ===== compose =====
    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompiler
    }
}

dependencies {
    api(project(":features:main:api"))
    api(project(":features:guide:api"))
    api(project(":features:auth:api"))
    api(project(":features:app_config:api"))
    implementation(project(":features:geo"))
    implementation(project(":features:landmark"))
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