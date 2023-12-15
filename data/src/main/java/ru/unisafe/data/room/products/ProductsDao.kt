package ru.unisafe.data.room.products

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {

    @Query("SELECT id, name, count, is_crossed FROM products WHERE shopping_list_id = :listId")
    fun getProductsByListId(listId: Int): Flow<List<ProductGetTuple>?>

    @Insert
    suspend fun addProduct(productDbEntity: ProductDbEntity)

    @Delete(ProductDbEntity::class)
    suspend fun deleteProduct(productDeleteTuple: ProductDeleteTuple)

    @Update(ProductDbEntity::class)
    suspend fun crossProductOff(productCrossOffTuple: ProductCrossOffTuple)

    @Query("SELECT is_crossed FROM products WHERE shopping_list_id = :listId AND id = :productId")
    suspend fun isProductCrossedOff(listId: Int, productId: Int): Boolean

    @Query("SELECT MAX(id) FROM products WHERE shopping_list_id = :listId")
    suspend fun getLastProductIdByList(listId: Int): Int
}