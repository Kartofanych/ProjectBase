plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
    id("org.jetbrains.kotlin.plugin.serialization")
    // Precompiled plugin with the base android configuration.
    // Declared in buildSrc/.../android-config.gradle.kts.
    `android-config`
}

android {
    namespace = ProjectConfig.namespace("geo")
}

dependencies {
    implementation(project(":common"))
    implementation(libs.core.ktx)

    implementation(libs.bundles.dagger)
    kapt(libs.bundles.daggerCompiler)

    implementation(libs.bundles.datastore)
    implementation(libs.json)
}