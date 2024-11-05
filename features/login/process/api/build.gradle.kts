plugins {
    id("com.android.library")
    id("kotlin-android")
    `android-config`
}

android {
    namespace = ProjectConfig.namespace("login.process.api")
}

dependencies {
    implementation(project(":common"))
    implementation(libs.core.ktx)

    implementation(libs.bundles.compose)
}