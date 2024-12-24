plugins {
    id("com.android.library")
    id("kotlin-android")
    `android-config`
}

android {
    namespace = ProjectConfig.namespace("promo.list.api")
}

dependencies {
    implementation(project(":common"))
    implementation(libs.core.ktx)

    implementation(libs.bundles.compose)
}