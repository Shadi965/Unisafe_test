package ru.unisafe.example

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.unisafe.data.auth.KeyDataRepository
import ru.unisafe.example.navigation.Navigator
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val navigator: Navigator,
    private val keyDataRepository: KeyDataRepository
) : ViewModel() {

    private val _currentKey = mutableStateOf<String?>(null)
    val currentKey: State<String?> = _currentKey

    init {
        viewModelScope.launch {
            _currentKey.value = keyDataRepository.getLastKey()
        }
    }

    fun getNavigator(): Navigator = navigator

}