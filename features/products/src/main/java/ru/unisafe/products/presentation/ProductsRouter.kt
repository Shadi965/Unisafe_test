package ru.unisafe.products.presentation

interface ProductsRouter {

    fun goBackToShoppingListsScreen()

    companion object {
        const val LIST_ID_ARG = "id"
        const val LIST_NAME_ARG = "name"
    }

}