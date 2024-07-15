plugins {
    id("com.android.library")
    id("kotlin-android")
    // Precompiled plugin with the base android configuration.
    // Declared in buildSrc/.../android-config.gradle.kts.
    `android-config`
}

android {
    namespace = ProjectConfig.namespace("audio_guide.api")
}

dependencies {
    implementation(project(":common"))
    implementation(libs.core.ktx)
}