package br.com.domotest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.domotest.domain.GetUserIdUseCase
import br.com.domotest.domain.LogoutUseCase
import br.com.domotest.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class MainViewModel(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val logoutUseCase: LogoutUseCase
): BaseViewModel() {

    private val _userLogged = MutableLiveData<Boolean>()
    val userLogged: LiveData<Boolean> = _userLogged

    fun getUserId() {
        launch {
            showLoading()
            getUserIdUseCase()?.let {
                _userLogged.postValue(true)
            } ?: run {
                _userLogged.postValue(false)
            }
            hideLoading()
        }
    }

    fun logout() {
        launch {
            logoutUseCase()
        }
    }
}