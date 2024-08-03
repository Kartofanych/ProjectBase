plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
    // Precompiled plugin with the base android configuration.
    // Declared in buildSrc/.../android-config.gradle.kts.
    `android-config`
}

android {
    namespace = ProjectConfig.namespace("app_config.impl")
}

dependencies {
    api(project(":features:app_config:api"))
    implementation(project(":common"))
    implementation(libs.core.ktx)

    implementation(libs.core.ktx)
    implementation(libs.bundles.compose)

    implementation(libs.bundles.dagger)
    kapt(libs.bundles.daggerCompiler)

    implementation(libs.bundles.datastore)
    implementation(libs.json)
}