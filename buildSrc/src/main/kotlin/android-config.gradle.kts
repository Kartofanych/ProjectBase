import com.android.build.gradle.BaseExtension

fun android(configuration: BaseExtension.() -> Unit) = configure(configuration)

// Common configuration for all Android modules.
android {
    compileSdkVersion(35)

    defaultConfig {
        minSdk = 28
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
