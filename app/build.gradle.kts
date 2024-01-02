plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    // Precompiled plugin with the base android configuration.
    // Declared in buildSrc/.../android-config.gradle.kts.
    `android-config`
}

android {
    namespace = ProjectConfig.applicationId

    defaultConfig {
        applicationId = ProjectConfig.applicationId
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName

        setProperty("archivesBaseName", "Multimodule-$versionName")
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    // ===== compose =====
    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompiler
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(":features:auth:impl"))

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.bundles.compose)

}
