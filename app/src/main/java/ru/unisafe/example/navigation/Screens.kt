package ru.unisafe.example.navigation

import ru.unisafe.products.presentation.ProductsRouter

sealed class Screen(
    val route: String,
    private val arguments: String = ""
) {

    val withArgument: String
        get() = route + arguments

    object Auth : Screen("auth_screen/")

    object ShoppingLists : Screen("shopping_lists_screen/")

    object Products : Screen("products_screen/",
        "{${ProductsRouter.LIST_ID_ARG}}/{${ProductsRouter.LIST_NAME_ARG}}")

}