plugins {
    id("com.android.library")
    id("kotlin-android")
    // Precompiled plugin with the base android configuration.
    // Declared in buildSrc/.../android-config.gradle.kts.
    `android-config`
}

android {
    namespace = ProjectConfig.namespace("auth")

    // ===== compose =====
    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompiler
    }
}

dependencies {
}