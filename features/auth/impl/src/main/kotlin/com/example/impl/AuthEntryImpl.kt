package com.example.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.api.AuthEntry
import com.example.api.AuthInteractor
import com.example.api.models.AuthInfo
import com.example.common.Destinations
import kotlinx.coroutines.delay
import javax.inject.Inject

class AuthEntryImpl @Inject constructor(
    private val authInteractor: AuthInteractor
) : AuthEntry() {
    @Composable
    override fun Composable(
        navController: NavHostController,
        destinations: Destinations,
        backStackEntry: NavBackStackEntry
    ) {
        //EXAMPLE
        LaunchedEffect(key1 = Unit) {
            authInteractor.updateAuthInfo(AuthInfo(id = "NEW ID"))
            delay(5000)
            authInteractor.updateAuthInfo(AuthInfo(id = "UPDATED ID"))
        }
        // view models, navigation here
        AuthScreen(authInteractor.authInfoFlow())
    }

}