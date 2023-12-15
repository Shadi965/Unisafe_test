package ru.unisafe.shopping_lists.presentation.screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import ru.unisafe.shopping_lists.domain.CreateShoppingListUseCase
import ru.unisafe.shopping_lists.domain.DeleteShoppingListUseCase
import ru.unisafe.shopping_lists.domain.GetCurrentKeyUseCase
import ru.unisafe.shopping_lists.domain.GetShoppingListsUseKeys
import ru.unisafe.shopping_lists.domain.LogoutUseCase
import ru.unisafe.shopping_lists.domain.entities.ShoppingList
import ru.unisafe.shopping_lists.domain.entities.ShoppingListView
import ru.unisafe.shopping_lists.presentation.ShoppingListsRouter
import javax.inject.Inject

@HiltViewModel
class ShoppingListsViewModel @Inject constructor(
    private val router: ShoppingListsRouter,
    private val deleteShoppingListUseCase: DeleteShoppingListUseCase,
    private val getShoppingListsUseKeys: GetShoppingListsUseKeys,
    private val getCurrentKeyUseCase: GetCurrentKeyUseCase,
    private val createShoppingListUseCase: CreateShoppingListUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    val showDialog = mutableStateOf(false)
    val newListName = mutableStateOf("")

    private val _list = MutableStateFlow<List<ShoppingList>?>(null)

    private val _currentKey = MutableStateFlow<String>("")
    val currentKey: StateFlow<String> = _currentKey

    private val _isCheckedAll = MutableStateFlow<Boolean>(false)
    val isCheckedAll: StateFlow<Boolean> = _isCheckedAll

    private val _checkedMode = MutableStateFlow(false)
    val checkedMode: StateFlow<Boolean> = _checkedMode

    private val _isCheckedListsIds = MutableStateFlow<Set<Int>>(emptySet())

    private val _inProgressListsIds = MutableStateFlow<Set<Int>>(emptySet())

    private val _isInLoading = MutableStateFlow<Boolean>(true)
    val isInLoading: StateFlow<Boolean> = _isInLoading

    val list: Flow<List<ShoppingListView>?> = combine(
        _list,
        _isCheckedListsIds,
        _inProgressListsIds,
        ::updateListFlow
    )

    init {
        _isInLoading.value = true
        viewModelScope.launch {
            launch {
                _currentKey.value = getCurrentKeyUseCase.getCurrentKey()
            }
            launch {
                _isCheckedListsIds.collect{ set ->
                    _checkedMode.value = set.isNotEmpty()
                    _isCheckedAll.value = _list.value?.firstOrNull { !set.contains(it.id) } == null
                }
            }
            getShoppingListsUseKeys.getShoppingLists().collect {
                _list.emit(it)
                _isInLoading.value = false
            }
        }
    }

    fun checkTheList(listId: Int) {
        val set =  _isCheckedListsIds.value.toMutableSet()
        if (!set.contains(listId)) {
            set.add(listId)
            _isCheckedListsIds.value = set
        } else {
            set.remove(listId)
            _isCheckedListsIds.value = set
        }
    }

    fun createNewList() {
        viewModelScope.launch {
            createShoppingListUseCase.createShoppingList(newListName.value)
        }
        newListName.value = ""
    }

    fun checkAllLists() {
        val set =  _isCheckedListsIds.value.toMutableSet()
        if (!_isCheckedAll.value)
            _list.value?.forEach {
                set.add(it.id)
            }
        else
            set.clear()
        _isCheckedListsIds.value = set
    }

    fun deleteList(listId: Int) {
        val set = _inProgressListsIds.value.toMutableSet()
        viewModelScope.launch {
            set.add(listId)
            _inProgressListsIds.value = set
            deleteShoppingListUseCase.deleteShoppingList(listId)
            set.remove(listId)
            _inProgressListsIds.value = set
        }
    }

    fun deleteCheckedLists() {
        val set = _isCheckedListsIds.value
        _isCheckedListsIds.value = emptySet()
        set.forEach{
            deleteList(it)
        }
    }

    fun openProductsScreen(listId: Int, listName: String) {
        router.openProductsScreen(listId, listName)
    }

    fun loadAgain() {
        _isInLoading.value = true
        viewModelScope.launch {
            getShoppingListsUseKeys.getShoppingLists()
            _isInLoading.value = false
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase.logout()
        }
    }

    private fun updateListFlow(
        shoppingLists: List<ShoppingList>?,
        checkedLists: Set<Int>,
        inProgressLists: Set<Int>
    ): List<ShoppingListView>? {
        return shoppingLists?.map {
            ShoppingListView(
                list = it,
                isChecked = checkedLists.contains(it.id),
                isInDeleting = inProgressLists.contains(it.id)
            )
        }
    }

}