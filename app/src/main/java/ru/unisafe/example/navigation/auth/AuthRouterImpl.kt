package ru.unisafe.example.navigation.auth

import ru.unisafe.auth.presentation.AuthRouter
import ru.unisafe.example.navigation.Navigator
import ru.unisafe.example.navigation.Screen
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRouterImpl @Inject constructor(
    private val navigator: Navigator
) : AuthRouter {
    override fun openShoppingListsScreen() {
        navigator {
            navigate(Screen.ShoppingLists) {
                popUpTo(Screen.Auth){
                    inclusive = true
                }
            }
        }
    }
}