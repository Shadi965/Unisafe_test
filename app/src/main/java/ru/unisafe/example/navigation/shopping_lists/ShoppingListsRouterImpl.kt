package ru.unisafe.example.navigation.shopping_lists

import ru.unisafe.example.navigation.Navigator
import ru.unisafe.example.navigation.Screen
import ru.unisafe.shopping_lists.presentation.ShoppingListsRouter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingListsRouterImpl @Inject constructor(
    private val navigator: Navigator
) : ShoppingListsRouter {
    override fun openProductsScreen(listId: Int) {
        navigator {
            navigate(Screen.Products.route + listId)
        }
    }
}