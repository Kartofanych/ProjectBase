import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties

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
        manifestPlaceholders["YANDEX_CLIENT_ID"] = getLocalProperty("CLIENT_ID")
        vectorDrawables {
            useSupportLibrary = true
        }
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
                keyPassword = getLocalProperty("KEY_PASSWORD").toString()
                storeFile = File("$projectDir/keys.jks")
                storePassword = getLocalProperty("KEY_PASSWORD").toString()
            }
            buildConfigField("String", "API_KEY", "\"${getLocalProperty("API_KEY")}\"")
        }

        getByName("debug") {
            buildConfigField("String", "API_KEY", "\"${getLocalProperty("API_KEY")}\"")
        }
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "17"
            freeCompilerArgs = listOf(
                "-Xopt-in=kotlin.RequiresOptIn",
                "-Xstring-concat=inline"
            )
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompiler
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

fun getLocalProperty(key: String, file: String = "local.properties"): Any {
    val properties = Properties()
    val localProperties = File(file)
    if (localProperties.isFile) {
        InputStreamReader(FileInputStream(localProperties), Charsets.UTF_8).use { reader ->
            properties.load(reader)
        }
    } else error("File from not found")

    return properties.getProperty(key)
}

dependencies {

    implementation(project(":common"))
    implementation(project(":features:auth:impl"))
    implementation(project(":features:login:impl"))
    implementation(project(":features:main:impl"))
    implementation(project(":features:guide:impl"))
    implementation(project(":features:audio_guide:impl"))
    implementation(project(":features:geo"))
    implementation(project(":features:app_config:impl"))

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
