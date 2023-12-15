package ru.unisafe.products.presentation.screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import ru.unisafe.products.domain.AddProductToListUseCase
import ru.unisafe.products.domain.CrossProductOffUseCase
import ru.unisafe.products.domain.GetProductsUseCase
import ru.unisafe.products.domain.RemoveProductFromListUseCase
import ru.unisafe.products.domain.UpdateDbUseCase
import ru.unisafe.products.domain.entities.Product
import ru.unisafe.products.domain.entities.ProductView
import ru.unisafe.products.presentation.ProductsRouter
import java.io.IOException
import java.lang.NumberFormatException
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val addProductToListUseCase: AddProductToListUseCase,
    private val removeProductFromListUseCase: RemoveProductFromListUseCase,
    private val crossProductOffUseCase: CrossProductOffUseCase,
    private val updateDbUseCase: UpdateDbUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val listId: Int = savedStateHandle[ProductsRouter.LIST_ID_ARG]!!
    val listName: String = savedStateHandle[ProductsRouter.LIST_NAME_ARG]!!

    val connectionError = mutableStateOf(false)
    val newProductName = mutableStateOf("")
    val newProductCount = mutableStateOf("")
    val productCountErrorInput = mutableStateOf(false)
    val showDialog = mutableStateOf(false)
    val showCrossedProducts = mutableStateOf(false)

    private val _products = MutableStateFlow<List<Product>?>(null)

    private val _isCheckedAll = MutableStateFlow<Boolean>(false)
    val isCheckedAll: StateFlow<Boolean> = _isCheckedAll

    private val _checkedMode = MutableStateFlow(false)
    val checkedMode: StateFlow<Boolean> = _checkedMode

    private val _isCheckedProductsIds = MutableStateFlow<Set<Int>>(emptySet())

    private val _inProgressProductsIds = MutableStateFlow<Set<Int>>(emptySet())

    private val _isInLoading = MutableStateFlow<Boolean>(true)
    val isInLoading: StateFlow<Boolean> = _isInLoading

    val products: Flow<List<ProductView>?> = combine(
        _products,
        _isCheckedProductsIds,
        _inProgressProductsIds,
        ::updateProductsFlow
    )

    init {
        connectionError.value = false
        viewModelScope.launch {
            launch {
                getProductsUseCase.getList(listId).collect{
                    _products.emit(it)
                }
            }
            launchDbSync()
            _isInLoading.value = false
            _isCheckedProductsIds.collect{ set ->
                _checkedMode.value = set.isNotEmpty()
                _isCheckedAll.value = _products.value?.firstOrNull { !set.contains(it.id) } == null
            }
        }
    }

    fun deleteProduct(productId: Int) {
        val set = _inProgressProductsIds.value.toMutableSet()
        viewModelScope.launch {
            set.add(listId)
            _inProgressProductsIds.value = set
            removeProductFromListUseCase.removeProductFromList(listId, productId)
            set.remove(listId)
            _inProgressProductsIds.value = set
        }
    }

    fun addProduct() {
        val name = newProductName.value
        val count: Int
        if (newProductCount.value.isBlank()) {
            productCountErrorInput.value = true
            return
        }
        try {
            count = newProductCount.value.toInt()
            productCountErrorInput.value = false
        } catch (e: NumberFormatException){
            productCountErrorInput.value = true
            return
        }
        viewModelScope.launch {
            addProductToListUseCase.addProductToList(listId, name, count)
        }
        showDialog.value = false
        newProductName.value = ""
        newProductCount.value = ""
    }

    fun checkProduct(productId: Int) {
        val set =  _isCheckedProductsIds.value.toMutableSet()
        if (!set.contains(productId)) {
            set.add(productId)
        } else {
            set.remove(productId)
        }
        _isCheckedProductsIds.value = set
    }

    fun checkAllProducts() {
        val set =  _isCheckedProductsIds.value.toMutableSet()
        if (!_isCheckedAll.value)
            _products.value?.forEach {
                set.add(it.id)
            }
        else
            set.clear()
        _isCheckedProductsIds.value = set
    }

    fun crossProductOff(productId: Int) {
        viewModelScope.launch {
            crossProductOffUseCase.crossProductOff(listId, productId)
        }
    }

    fun crossOffEveryCheckedProducts() {
        val set = _isCheckedProductsIds.value
        _isCheckedProductsIds.value = emptySet()
        set.forEach{
            crossProductOff(it)
        }
    }

    fun deleteEveryCheckedProducts() {
        val set = _isCheckedProductsIds.value
        _isCheckedProductsIds.value = emptySet()
        set.forEach{
            deleteProduct(it)
        }
    }

    private fun updateProductsFlow(
        products: List<Product>?,
        checkedProducts: Set<Int>,
        inProgressProducts: Set<Int>
    ): List<ProductView>? {
        return products?.map {
            ProductView(
                item = it,
                isChecked = checkedProducts.contains(it.id),
                isInProgress = inProgressProducts.contains(it.id)
            )
        }
    }

    fun launchDbSync() {
        connectionError.value = false
        _isInLoading.value = true
        viewModelScope.launch {
            launch {
                while (true) {
                    try {
                        delay(5000)
                        updateDbUseCase.updateDb(listId)
                    } catch (e: IOException) {
                        connectionError.value = true
                        break
                    }
                }
            }
            delay(5000)
            _isInLoading.value = false
        }
    }

}