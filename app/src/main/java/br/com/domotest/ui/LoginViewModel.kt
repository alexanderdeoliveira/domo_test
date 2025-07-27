package br.com.domotest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.domotest.domain.GenerateUUIDUseCase
import br.com.domotest.domain.SaveUserIdUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val generateUUIDUseCase: GenerateUUIDUseCase,
    private val saveUserIdUseCase: SaveUserIdUseCase
): BaseViewModel() {

    private val _loginState = MutableLiveData<Boolean>()
    val loginState: LiveData<Boolean> = _loginState

    fun login() {
        launch {
            generateUUIDUseCase { userId ->
                if (userId.isNullOrEmpty()) {
                    _loginState.postValue(false)
                } else {
                    saveUserId(userId)
                    _loginState.postValue(true)
                }
            }
        }
    }

    private fun saveUserId(userId: String) {
        launch {
            saveUserIdUseCase(userId)
        }
    }
}