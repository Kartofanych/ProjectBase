plugins {
    id("com.android.library")
    id("kotlin-android")
    `android-config`
}

android {
    namespace = ProjectConfig.namespace("favourites.api")
}

dependencies {
    implementation(project(":common"))
    implementation(libs.core.ktx)
}