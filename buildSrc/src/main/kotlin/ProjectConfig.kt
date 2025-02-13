object ProjectConfig {
    const val applicationId = "com.example.multimodulepractice"
    const val versionCode = 3
    const val versionName = "1.0.2"

    fun namespace(feature: String) = "$applicationId.$feature"
}