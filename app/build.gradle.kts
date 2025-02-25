import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.archivesName
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
    `android-config`
}

android {
    namespace = ProjectConfig.applicationId

    defaultConfig {
        applicationId = ProjectConfig.applicationId
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName
        archivesName = "TimeToTravel-$versionName"
        manifestPlaceholders["YANDEX_CLIENT_ID"] = getLocalProperty("CLIENT_ID")
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
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
            buildConfigField("String", "METRICA_KEY", "\"${getLocalProperty("METRICA_KEY")}\"")
        }

        getByName("debug") {
            buildConfigField("String", "API_KEY", "\"${getLocalProperty("API_KEY")}\"")
            buildConfigField("String", "METRICA_KEY", "\"${getLocalProperty("METRICA_KEY")}\"")
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

    kapt {
        useBuildCache = false
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
    implementation(project(":features:filters:impl"))
    implementation(project(":features:splash:impl"))
    implementation(project(":features:service:impl"))
    implementation(project(":features:search:impl"))
    implementation(project(":features:attraction:impl"))
    implementation(project(":features:main_favourites:impl"))
    implementation(project(":features:promo:impl"))
    implementation(project(":features:reviews:impl"))
    implementation(project(":features:onboarding:impl"))

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.splash)

    implementation(libs.bundles.navigation)

    implementation(libs.bundles.compose)

    implementation(libs.bundles.dagger)
    kapt(libs.bundles.daggerCompiler)

    implementation(libs.maps.mobile)

    implementation(libs.bundles.network)

    implementation(libs.analytics)
}
