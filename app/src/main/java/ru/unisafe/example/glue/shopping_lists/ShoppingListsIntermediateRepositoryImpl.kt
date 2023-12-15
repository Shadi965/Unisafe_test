package ru.unisafe.example.glue.shopping_lists

import android.annotation.SuppressLint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.unisafe.data.shopping_lists.ShoppingListsDataRepository
import ru.unisafe.shopping_lists.domain.entities.ShoppingList
import ru.unisafe.shopping_lists.domain.repositories.ShoppingListsRepository
import java.text.SimpleDateFormat
import java.util.TimeZone
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ShoppingListsIntermediateRepositoryImpl @Inject constructor(
    private val shoppingListsDataRepository: ShoppingListsDataRepository
): ShoppingListsRepository {

    override suspend fun getShoppingListsByKey(key: String): Flow<List<ShoppingList>?> {
        @SuppressLint("SimpleDateFormat")
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val tz = dateFormat.timeZone
        return shoppingListsDataRepository.getAllShoppingLists(key).map {
            it?.map { list ->
                dateFormat.timeZone = TimeZone.getTimeZone("00:00")
                val date = dateFormat.parse(list.createdAt)!!
                dateFormat.timeZone = tz
                val formattedDate = dateFormat.format(date)
                ShoppingList(
                    id = list.id,
                    name = list.name,
                    createdAt = formattedDate
                )
            }
        }
    }

    override suspend fun createShoppingList(name: String) {
        shoppingListsDataRepository.createShoppingList(name)
    }

    override suspend fun deleteShoppingList(listId: Int) {
        shoppingListsDataRepository.removeShoppingList(listId)
    }
}