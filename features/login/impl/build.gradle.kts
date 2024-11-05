plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
    // Precompiled plugin with the base android configuration.
    // Declared in buildSrc/.../android-config.gradle.kts.
    `android-config`
}

android {
    namespace = ProjectConfig.namespace("login.impl")

    // ===== compose =====
    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompiler
    }
}

dependencies {
    api(project(":features:login:api"))
    api(project(":features:main:api"))
    api(project(":features:auth:api"))
    implementation(project(":features:login:start:impl"))
    implementation(project(":features:login:process:impl"))
    implementation(project(":common"))
    implementation(libs.core.ktx)

    implementation(libs.bundles.compose)

    implementation(libs.bundles.dagger)
    kapt(libs.bundles.daggerCompiler)

    implementation(libs.auth.sdk)

    implementation(libs.bundles.network)
}