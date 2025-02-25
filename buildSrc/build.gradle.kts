plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
    kotlin("android") version "1.6.21" apply false
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    implementation(libs.gradle)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.kotlin.serialization)

    annotationProcessor(libs.bundles.daggerCompiler)

    //use when watching layout inspector
    //implementation(libs.androidx.ui.tooling)
    //implementation(libs.androidx.ui.tooling.preview)
}
