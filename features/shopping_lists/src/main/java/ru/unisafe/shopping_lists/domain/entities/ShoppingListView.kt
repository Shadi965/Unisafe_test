package ru.unisafe.shopping_lists.domain.entities

data class ShoppingListView(
    val list: ShoppingList,
    val isChecked: Boolean,
    val isInDeleting: Boolean,
)
