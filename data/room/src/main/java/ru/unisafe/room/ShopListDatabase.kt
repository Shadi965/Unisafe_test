package ru.unisafe.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.unisafe.room.products.ProductDbEntity
import ru.unisafe.room.products.ProductsDao
import ru.unisafe.room.shopping_lists.ShoppingListDbEntity
import ru.unisafe.room.shopping_lists.ShoppingListsDao

@Database(
    version = 1,
    entities = [
        ShoppingListDbEntity::class,
        ProductDbEntity::class
    ]
)
abstract class ShopListDatabase : RoomDatabase() {

    abstract fun getShoppingListsDao(): ShoppingListsDao

    abstract fun getProductsDao(): ProductsDao

}