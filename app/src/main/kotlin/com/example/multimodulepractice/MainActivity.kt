package com.example.multimodulepractice

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.api.AuthInfoManager
import com.example.common.theme.MultimodulePracticeTheme
import com.inno.api.GuideEntry
import com.inno.api.LoginFeatureEntry
import com.inno.api.MainFeatureEntry
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainFeatureApi: MainFeatureEntry

    @Inject
    lateinit var loginFeatureApi: LoginFeatureEntry

    @Inject
    lateinit var guideEntry: GuideEntry

    @Inject
    lateinit var authInfoManager: AuthInfoManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(
            ComposeView(context = this).apply {
                this.setContent {
                    MultimodulePracticeTheme {
                        NavigatorScaffold()
                    }
                }
            }.also {
                ViewCompat.setOnApplyWindowInsetsListener(it) { v, windowInsets ->
                    val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
                    v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                        leftMargin = insets.left
                        bottomMargin = insets.bottom
                        rightMargin = insets.right
                    }
                    WindowInsetsCompat.CONSUMED
                }
            }
        )
    }

    @Composable
    private fun NavigatorScaffold() {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = when (authInfoManager.authInfo().token) {
                null -> loginFeatureApi.featureRoute
                else -> mainFeatureApi.featureRoute
            }
        ) {
            register(
                loginFeatureApi,
                navController = navController,
                modifier = Modifier
            )

            register(
                mainFeatureApi,
                navController = navController,
                modifier = Modifier
            )

            register(
                guideEntry,
                navController = navController,
                modifier = Modifier
            )
        }

    }
}