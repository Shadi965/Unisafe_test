package ru.unisafe.example.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.unisafe.auth.presentation.screen.AuthScreen
import ru.unisafe.products.presentation.ProductsRouter
import ru.unisafe.products.presentation.screen.ProductsScreen
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
            arguments = listOf(
                navArgument(ProductsRouter.LIST_ID_ARG) {
                type = NavType.IntType
                },
                navArgument(ProductsRouter.LIST_NAME_ARG) {
                    type = NavType.StringType
                }
            )
        ) {
            ProductsScreen()
        }
    }

    fun register(navController: NavHostController) {
        this.navController = navController
    }

    @Composable
    fun AddNavHost(startDestination: String) {
        NavHost(
            navController = navController!!,
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