package ru.unisafe.room.shopping_lists

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListsDao {

    @Query("SELECT id, name, created_at FROM shopping_lists WHERE `key` = :key")
    fun getShoppingListsByKey(key: String): Flow<List<ShoppingListGetTuple>?>

    @Delete(entity = ShoppingListDbEntity::class)
    suspend fun deleteShoppingListById(shoppingListDeleteTuple: ShoppingListDeleteTuple)

    @Delete(entity = ShoppingListDbEntity::class)
    suspend fun deleteShoppingListsByKey(shoppingListsDeleteByKeyTuple: ShoppingListsDeleteByKeyTuple)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addShoppingList(shoppingListDbEntity: ShoppingListDbEntity)

    @Query("Select MAX(id) FROM shopping_lists WHERE `key` = :key")
    suspend fun getLastListIdByKey(key: String): Int

}