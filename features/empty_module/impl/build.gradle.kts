plugins {
    id("com.android.library")
    id("kotlin-android")
    `android-config`
}

android {
    namespace = ProjectConfig.namespace("empty_module.impl")
}

dependencies {
    api(project(":features:empty_module:api"))
    implementation(project(":common"))
    implementation(libs.core.ktx)
}