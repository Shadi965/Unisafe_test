package ru.unisafe.auth.presentation.screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.unisafe.auth.domain.GetDefaultKeyUseKeys
import ru.unisafe.auth.domain.GetNewKeyUseCase
import ru.unisafe.auth.domain.IsKeyCorrectUseCase
import ru.unisafe.auth.domain.SaveKeyUseCase
import ru.unisafe.auth.presentation.AuthRouter
import javax.inject.Inject

@HiltViewModel
class AuthScreenViewModel @Inject constructor(
    private val getNewKeyUseCase: GetNewKeyUseCase,
    private val isKeyCorrectUseCase: IsKeyCorrectUseCase,
    private val saveKeyUseCase: SaveKeyUseCase,
    private val getDefaultKeyUseKeys: GetDefaultKeyUseKeys,
    private val router: AuthRouter
) : ViewModel() {

    val key = mutableStateOf("")
    val isKeyIncorrect = mutableStateOf(false)
    val isKeyVerified = mutableStateOf(false)
    val isInProgress = mutableStateOf(false)

    fun getNewKey() = viewModelScope.launch {
        isInProgress.value = true
        try {
            key.value = getNewKeyUseCase.getNewKey()
            isKeyIncorrect.value = false
            isKeyVerified.value = true
        } catch (ex: Exception) { //todo
            throw ex
        } finally {
            isInProgress.value = false
        }
    }

    fun sendKey(){
        if (key.value.isBlank())
            getDefaultKey()
        else
            verifyKey()

    }

    private fun getDefaultKey() = viewModelScope.launch {
        isInProgress.value = true
        key.value = getDefaultKeyUseKeys.getDefaultKey()
        isInProgress.value = false
    }

    private fun verifyKey() = viewModelScope.launch {
        isInProgress.value = true
        if (isKeyVerified.value) saveKey()
        else
            try {
                isKeyVerified.value = isKeyCorrectUseCase.isKeyCorrect(key.value)
                isKeyIncorrect.value = !isKeyVerified.value
            } catch (ex: Exception) { //todo
                throw ex
            }
        if (isKeyVerified.value) saveKey()
    }

    private fun saveKey() = viewModelScope.launch {
        saveKeyUseCase.saveKey(key.value)
        router.openShoppingListsScreen()
    }


}