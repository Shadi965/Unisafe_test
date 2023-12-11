package ru.unisafe.example.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.unisafe.auth.presentation.screen.AuthScreen

typealias NavAction = (NavHostController) -> Unit

object Navigator {

    @SuppressLint("StaticFieldLeak")
    private var navController: NavHostController? = null
        set(value) {
            field = value
            if (value != null) {
                actions.forEach {
                    it(value)
                }
                actions.clear()
            }
        }

    private val navGraph: NavGraphBuilder.() -> Unit = {
        composable(route = Screen.Auth) {
            AuthScreen()
        }
    }

    @Composable
    fun Register(navController: NavHostController, startDestination: String) {
        this.navController = navController
        NavHost(
            navController = navController,
            startDestination = startDestination,
            builder = navGraph
        )
    }

    fun unRegister() {
        this.navController = null
    }

    private val actions = mutableListOf<NavAction>()

    operator fun invoke(navAction: NavAction) {
        if (navController == null) actions += navAction
        else navAction(navController!!)
    }
}