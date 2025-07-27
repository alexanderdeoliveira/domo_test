package br.com.domotest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.domotest.domain.GenerateUUIDUseCase
import br.com.domotest.domain.LoginUseCase
import kotlinx.coroutines.launch

class LoginViewModel(
    private val generateUUIDUseCase: GenerateUUIDUseCase,
    private val loginUseCase: LoginUseCase
): BaseViewModel() {

    private val _userLogged = MutableLiveData<Boolean>()
    val userLogged: LiveData<Boolean> = _userLogged

    fun login() {
        launch {
            generateUUIDUseCase { userId ->
                if (userId.isNullOrEmpty()) {
                    _userLogged.postValue(false)
                } else {
                    saveUserId(userId)
                    _userLogged.postValue(true)
                }
            }
        }
    }

    private fun saveUserId(userId: String) {
        launch {
            loginUseCase(userId)
        }
    }
}