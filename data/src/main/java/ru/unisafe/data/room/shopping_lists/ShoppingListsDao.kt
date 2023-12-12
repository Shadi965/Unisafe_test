package ru.unisafe.data.room.shopping_lists

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListsDao {

    @Query("SELECT id, name, created_at FROM shopping_lists WHERE `key` = :key")
    fun getShoppingListsByKey(key: String): Flow<List<ShoppingListGetTuple>?>

    @Delete(entity = ShoppingListDbEntity::class)
    suspend fun deleteShoppingListById(shoppingListDeleteTuple: ShoppingListDeleteTuple)

    @Insert
    suspend fun addShoppingList(shoppingListDbEntity: ShoppingListDbEntity)

}