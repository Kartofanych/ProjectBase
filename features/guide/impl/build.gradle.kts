plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    // Precompiled plugin with the base android configuration.
    // Declared in buildSrc/.../android-config.gradle.kts.
    `android-config`
}

android {
    namespace = ProjectConfig.namespace("login")

    // ===== compose =====
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

    implementation(libs.bundles.hilt)
    kapt(libs.hilt.android.compiler)

    implementation(libs.auth.sdk)

    implementation(libs.bundles.network)
}