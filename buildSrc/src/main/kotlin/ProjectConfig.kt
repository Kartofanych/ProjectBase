object ProjectConfig {
    const val applicationId = "com.example.multimodulepractice"
    const val versionCode = 1
    const val versionName = "1.0.0"

    fun namespace(feature: String) = "$applicationId.$feature"
}