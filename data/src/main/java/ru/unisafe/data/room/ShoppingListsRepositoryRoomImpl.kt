package ru.unisafe.data.room

import android.annotation.SuppressLint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.unisafe.data.auth.KeyDataRepository
import ru.unisafe.data.room.shopping_lists.ShoppingListDbEntity
import ru.unisafe.data.room.shopping_lists.ShoppingListDeleteTuple
import ru.unisafe.data.room.shopping_lists.ShoppingListsDao
import ru.unisafe.data.room.shopping_lists.ShoppingListsDeleteByKeyTuple
import ru.unisafe.data.retrofit.shoping_lists.entities.ShoppingListDTO
import ru.unisafe.data.shopping_lists.ShoppingListsDataRepository
import ru.unisafe.data.shopping_lists.ShoppingListsSource
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingListsRepositoryRoomImpl @Inject constructor(
    private val shoppingListsDao: ShoppingListsDao,
    private val shoppingListsSource: ShoppingListsSource,
    private val keyDataRepository: KeyDataRepository
) : ShoppingListsDataRepository {
    override suspend fun getAllShoppingLists(key: String): Flow<List<ShoppingListDTO>?> = withContext(Dispatchers.IO) {
        launch {
            val list = shoppingListsSource.getAllShoppingLists(key)
            shoppingListsDao.deleteShoppingListsByKey(ShoppingListsDeleteByKeyTuple(key))
            list.forEach {
                shoppingListsDao.addShoppingList(
                    ShoppingListDbEntity(
                        key = key,
                        id = it.id,
                        name = it.name,
                        createdAt = it.createdAt
                    )
                )
            }
        }
        return@withContext shoppingListsDao.getShoppingListsByKey(key).map {
            it?.map { list ->
                ShoppingListDTO(
                    id = list.id,
                    name = list.name,
                    createdAt = list.createdAt
                )
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    override suspend fun createShoppingList(name: String) {
        val key = keyDataRepository.getLastKey() ?: throw IllegalStateException() //todo
        val newListId = shoppingListsSource.createShoppingList(key, name)
        val currentTime = SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Date(System.currentTimeMillis()))
        shoppingListsDao.addShoppingList(
            ShoppingListDbEntity(
                key = key,
                id = newListId,
                name = name,
                createdAt = currentTime
            )
        )
    }

    override suspend fun removeShoppingList(listId: Int) = withContext(Dispatchers.IO) {
        launch {
            shoppingListsSource.removeShoppingList(listId)
        }
        shoppingListsDao.deleteShoppingListById(ShoppingListDeleteTuple(listId))
    }
}