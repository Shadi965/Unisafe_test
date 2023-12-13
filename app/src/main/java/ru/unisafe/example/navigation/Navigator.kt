package ru.unisafe.example.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.unisafe.auth.presentation.screen.AuthScreen
import ru.unisafe.shopping_lists.presentation.screen.ShoppingListsScreen

typealias NavAction = NavHostController.() -> Unit

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

    private val actions = mutableListOf<NavAction>()

    private val navGraph: NavGraphBuilder.() -> Unit = {
        composable(route = Screen.Auth.route) {
            AuthScreen()
        }
        composable(route = Screen.ShoppingLists.route) {
            ShoppingListsScreen()
        }
        composable(
            route = Screen.Products.withArgument,
            arguments = listOf(navArgument("id") {
                type = NavType.IntType
            })
        ) {
            Text(text = "Products Screen ${it.arguments?.getInt("id")?.toString()}", fontSize = 32.sp)
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

    operator fun invoke(navAction: NavAction) {
        if (navController == null) actions += navAction
        else navAction(navController!!)
    }

    fun clear() = actions.clear()
}