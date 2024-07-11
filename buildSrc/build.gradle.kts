plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
    kotlin("android") version "1.4.20" apply false
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation(libs.gradle)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.kotlin.serialization)

    //use when watching layout inspector
    //implementation(libs.androidx.ui.tooling)
    //implementation(libs.androidx.ui.tooling.preview)
}
