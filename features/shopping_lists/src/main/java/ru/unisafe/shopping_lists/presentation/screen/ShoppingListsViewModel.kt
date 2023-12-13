package ru.unisafe.shopping_lists.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
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
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {

    private val _list = MutableStateFlow<List<ShoppingList>?>(null)

    private val _currentKey = MutableStateFlow<String>("")
    val currentKey: StateFlow<String> = _currentKey

    private val _isCheckedAll = MutableStateFlow<Boolean>(false)
    val isCheckedAll: StateFlow<Boolean> = _isCheckedAll

    private val _checkedMode = MutableStateFlow(false)
    val checkedMode: StateFlow<Boolean> = _checkedMode

    private val _isCheckedListsIds = MutableStateFlow<Set<Int>>(emptySet())

    private val inProgressListsIds = MutableStateFlow<Set<Int>>(emptySet())

    private val _isInLoading = MutableStateFlow<Boolean>(true)
    val isInLoading: StateFlow<Boolean> = _isInLoading

    val list: Flow<List<ShoppingListView>?> = combine(
        _list,
        _isCheckedListsIds,
        inProgressListsIds,
        ::updateListFlow
    )

    init {
        _isInLoading.value = true
        viewModelScope.launch {
            launch {
                _currentKey.value = getCurrentKeyUseCase.getCurrentKey()
            }
            launch {
                _isCheckedListsIds.collect{
                    _checkedMode.value = it.isNotEmpty()
                    var isContainedAll: Boolean = true
                    _list.value?.forEach { list ->
                        if (!it.contains(list.id))
                            isContainedAll = false
                    }
                    _isCheckedAll.value = isContainedAll
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
        val set = inProgressListsIds.value.toMutableSet()
        viewModelScope.launch {
            set.add(listId)
            inProgressListsIds.value = set
            deleteShoppingListUseCase.deleteShoppingList(listId)
            set.remove(listId)
            inProgressListsIds.value = set
        }
    }

    fun deleteCheckedLists() {
        val set = _isCheckedListsIds.value
        _isCheckedListsIds.value = emptySet()
        set.forEach{
            deleteList(it)
        }

    }

    fun openProductsScreen(listId: Int) {
        router.openProductsScreen(listId)
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