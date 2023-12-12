package ru.unisafe.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.unisafe.data.room.products.ProductDbEntity
import ru.unisafe.data.room.products.ProductsDao
import ru.unisafe.data.room.shopping_lists.ShoppingListDbEntity
import ru.unisafe.data.room.shopping_lists.ShoppingListsDao

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