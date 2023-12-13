package ru.unisafe.example.navigation

import kotlin.reflect.KProperty

sealed class Screen(
    val route: String,
    private val arguments: String = ""
) {

    val withArgument: String
        get() = route + arguments

    object Auth : Screen("auth_screen/")

    object ShoppingLists : Screen("shopping_lists_screen/")

    object Products : Screen("products_screen/", "{id}")

}