plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("com.google.gms.google-services")
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
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.create("release").apply {
                keyAlias = "travelling"
                keyPassword = "AiratRegina55"
                storeFile = File("$projectDir/keys.jks")
                storePassword = "AiratRegina55"
            }
        }
    }
}

dependencies {

    implementation(project(":common"))
    implementation(project(":features:auth:impl"))
    implementation(project(":features:login:impl"))
    implementation(project(":features:main:impl"))
    implementation(project(":features:guide:impl"))
    implementation(project(":features:geo"))

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)

    implementation(libs.bundles.navigation)

    implementation(libs.bundles.compose)

    implementation(libs.bundles.dagger)
    kapt(libs.bundles.daggerCompiler)

    implementation(libs.maps.mobile)

    implementation(libs.bundles.network)

    //TODO
    implementation(platform("com.google.firebase:firebase-bom:33.1.1"))
    implementation("com.google.firebase:firebase-analytics")

}
