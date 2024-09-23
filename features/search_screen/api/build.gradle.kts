plugins {
    id("com.android.library")
    id("kotlin-android")
    `android-config`
}

android {
    namespace = ProjectConfig.namespace("search_screen.api")
}

dependencies {
    implementation(project(":common"))
    implementation(libs.core.ktx)

    implementation(libs.bundles.compose)
}