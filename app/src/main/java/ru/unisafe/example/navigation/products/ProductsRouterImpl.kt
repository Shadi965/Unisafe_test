package ru.unisafe.example.navigation.products

import ru.unisafe.example.navigation.Navigator
import ru.unisafe.products.presentation.ProductsRouter
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsRouterImpl @Inject constructor(
    private val navigator: Navigator
): ProductsRouter {
    override fun goBackToShoppingListsScreen() {
        navigator{
            popBackStack()
        }
    }
}