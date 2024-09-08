plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
    `android-config`
}

android {
    namespace = ProjectConfig.namespace("splash.impl")

    defaultConfig {
        buildConfigField("String", "VERSION_NAME", "\"${ProjectConfig.versionName}\"")
    }

    buildFeatures.buildConfig = true
    buildFeatures.compose = true
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompiler
    }
}

dependencies {
    api(project(":features:splash:api"))
    api(project(":features:auth:api"))
    api(project(":features:filters:api"))
    implementation(project(":common"))
    implementation(project(":features:geo"))
    implementation(libs.core.ktx)

    implementation(libs.bundles.compose)

    implementation(libs.bundles.dagger)
    kapt(libs.bundles.daggerCompiler)

    implementation(libs.auth.sdk)

    implementation(libs.bundles.network)
}