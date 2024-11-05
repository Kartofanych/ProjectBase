plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
    `android-config`
}

android {
    namespace = ProjectConfig.namespace("login.start.impl")

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
    api(project(":features:login:start:api"))
    api(project(":features:auth:api"))
    implementation(project(":common"))
    implementation(libs.core.ktx)

    implementation(libs.bundles.compose)

    implementation(libs.bundles.dagger)
    kapt(libs.bundles.daggerCompiler)

    implementation(libs.auth.sdk)

    implementation(libs.bundles.network)
}