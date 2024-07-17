plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
    // Precompiled plugin with the base android configuration.
    // Declared in buildSrc/.../android-config.gradle.kts.
    `android-config`
}

android {
    namespace = ProjectConfig.namespace("audio_guide.impl")

    // ===== compose =====
    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompiler
    }
}

dependencies {
    api(project(":features:audio_guide:api"))
    implementation(project(":common"))

    implementation(libs.core.ktx)

    implementation(libs.bundles.compose)

    implementation(libs.bundles.dagger)
    kapt(libs.bundles.daggerCompiler)

}