package ru.unisafe.data.room

import android.annotation.SuppressLint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.unisafe.data_common.auth.KeyDataRepository
import ru.unisafe.data.room.shopping_lists.ShoppingListDbEntity
import ru.unisafe.data.room.shopping_lists.ShoppingListDeleteTuple
import ru.unisafe.data.room.shopping_lists.ShoppingListsDao
import ru.unisafe.data_common.shopping_lists.ShoppingListsDataRepository
import ru.unisafe.data_common.shopping_lists.ShoppingListsSource
import ru.unisafe.data_common.shopping_lists.entities.ShoppingList
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingListsRepositoryRoomImpl @Inject constructor(
    private val shoppingListsDao: ShoppingListsDao,
    private val shoppingListsSource: ShoppingListsSource,
    private val keyDataRepository: KeyDataRepository
) : ShoppingListsDataRepository {

    private suspend fun updateDb(key: String) {
        val netList = shoppingListsSource.getAllShoppingLists(key)
        val netListsIds = netList.map { it.id }.toSet()
        val dbListsIds = shoppingListsDao.getShoppingListsByKey(key).first()?.map { it.id }?.toSet() ?: emptySet()

        val toBeAddedListsIds = netListsIds.toMutableSet().apply { removeAll(dbListsIds) }
        val toBeDeletedListsIds = dbListsIds.toMutableSet().apply { removeAll(netListsIds) }

        val toBeAddedLists = netList.filter {
            toBeAddedListsIds.contains(it.id)
        }

        toBeDeletedListsIds.forEach {
            shoppingListsDao.deleteShoppingListById(ShoppingListDeleteTuple(it)) }
        toBeAddedLists.forEach {
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

    override suspend fun getAllShoppingLists(key: String): Flow<List<ShoppingList>?> = withContext(Dispatchers.IO) {
        try {
            updateDb(key)
        } catch (e: IOException) {
            //todo
        }
        return@withContext shoppingListsDao.getShoppingListsByKey(key).map {
            it?.map { list ->
                ShoppingList(
                    id = list.id,
                    name = list.name,
                    createdAt = list.createdAt
                )
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    override suspend fun createShoppingList(name: String) {
        val key = keyDataRepository.getLastKey() ?: throw IllegalStateException()
        val newListId = try {
            shoppingListsSource.createShoppingList(key, name)
        } catch (e: IOException) {
            //todo
            shoppingListsDao.getLastListIdByKey(key) + 1
        }

        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        dateFormat.timeZone = TimeZone.getTimeZone("00:00")
        val currentTime = dateFormat.format(Date(System.currentTimeMillis()))

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
            try {
                shoppingListsSource.removeShoppingList(listId)
            } catch (e: IOException) {
                //todo
            }
        }
        shoppingListsDao.deleteShoppingListById(ShoppingListDeleteTuple(listId))
    }
}