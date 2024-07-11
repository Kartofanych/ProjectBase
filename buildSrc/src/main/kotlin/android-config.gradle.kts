import com.android.build.gradle.BaseExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun android(configuration: BaseExtension.() -> Unit) = configure(configuration)

// Common configuration for all Android modules.
android {
    compileSdkVersion(34)

    defaultConfig {
        minSdk = 28
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        manifestPlaceholders["YANDEX_CLIENT_ID"] = "4099a25062b54be98363d406b389760d"
    }
    buildTypes {
        named("release") {
            isMinifyEnabled = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
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
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
