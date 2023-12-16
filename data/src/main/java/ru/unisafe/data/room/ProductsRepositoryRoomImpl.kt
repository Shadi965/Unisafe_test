package ru.unisafe.data.room

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import ru.unisafe.data_common.products.ProductsDataRepository
import ru.unisafe.data_common.products.ProductsSource
import ru.unisafe.data_common.products.entity.Product
import ru.unisafe.data.room.products.ProductCrossOffTuple
import ru.unisafe.data.room.products.ProductDbEntity
import ru.unisafe.data.room.products.ProductDeleteTuple
import ru.unisafe.data.room.products.ProductsDao
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsRepositoryRoomImpl @Inject constructor(
    private val productsSource: ProductsSource,
    private val productsDao: ProductsDao
) : ProductsDataRepository {
    override suspend fun getProductsList(listId: Int): Flow<List<Product>?> {
        try {
            updateDb(listId)
        } catch (e: IOException) {
            //todo
        }
        return productsDao.getProductsByListId(listId).map { list ->
            list?.map {
                Product(
                    id = it.id,
                    name = it.name,
                    count = it.count,
                    isCrossed = it.isCrossed
                )
            } ?: emptyList()
        }
    }

    override suspend fun addProductToList(listId: Int, productName: String, count: Int) {
        val productId = try {
            productsSource.addNewProductToList(listId, productName, count)
        } catch (e: IOException) {
            //todo
            productsDao.getLastProductIdByList(listId)
        }
        productsDao.addProduct(
            ProductDbEntity(
                shoppingListId = listId,
                id = productId,
                name = productName,
                count = count,
                isCrossed = false
            )
        )
    }

    override suspend fun removeProduct(productId: Int, listId: Int) {
        try {
            //listId для запроса установлен по умолчанию на 0, так как он всё равно
            //никак не влияет на результат, но требуется для выполения запроса
            productsSource.removeProductFromList(productId)
        } catch (e: IOException) {
            //todo
        }
        productsDao.deleteProduct(ProductDeleteTuple(listId, productId))
    }

    override suspend fun crossProductOff(listId: Int, productId: Int) {
        try {
            productsSource.crossProductOff(productId)
        } catch (e: IOException) {
            //todo
        }
        val isCrossed = productsDao.isProductCrossedOff(listId, productId)
        productsDao.crossProductOff(ProductCrossOffTuple(listId, productId, !isCrossed))
    }

    override suspend fun updateDb(listId: Int) {
        val netList = productsSource.getProductsList(listId)
        val netProductsIds = netList.map { it.id }.toSet()
        val dbList = productsDao.getProductsByListId(listId).first() ?: emptyList()
        val dbProductsIds = dbList.map { it.id }.toSet()

        val commonProductsIds = netProductsIds.filter {
            dbProductsIds.contains(it)
        }
        val toBeCrossedChangeIds = commonProductsIds.filter { productId ->
            netList.first { it.id == productId }.isCrossed != dbList.first { it.id == productId }.isCrossed
        }

        val toBeAddedProductsIds = netProductsIds.toMutableSet().apply { removeAll(dbProductsIds) }
        val toBeDeletedProductsIds = dbProductsIds.toMutableSet().apply { removeAll(netProductsIds) }

        val toBeAddedProducts = netList.filter {
            toBeAddedProductsIds.contains(it.id)
        }

        toBeCrossedChangeIds.forEach {
            val isCrossed = productsDao.isProductCrossedOff(listId, it)
            productsDao.crossProductOff(ProductCrossOffTuple(listId, it, !isCrossed))
        }

        toBeDeletedProductsIds.forEach {
            productsDao.deleteProduct(ProductDeleteTuple(listId, it)) }
        toBeAddedProducts.forEach {
            productsDao.addProduct(
                ProductDbEntity(
                    shoppingListId = listId,
                    id = it.id,
                    name = it.name,
                    count = it.count,
                    isCrossed = it.isCrossed
                )
            )
        }
    }
}