package br.com.domotest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.domotest.domain.DeleteAllTodosUseCase
import br.com.domotest.domain.GetUserIdUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val getUserIdUseCase: GetUserIdUseCase,
    private val deleteAllTodosUseCase: DeleteAllTodosUseCase
): BaseViewModel() {

    private val _userLogged = MutableLiveData<Boolean>()
    val userLogged: LiveData<Boolean> = _userLogged

    fun getUserId() {
        launch {
            getUserIdUseCase()?.let {
                _userLogged.postValue(true)
            } ?: run {
                _userLogged.postValue(false)
            }
        }
    }

    fun deleteAllTodos() {
        launch {
            deleteAllTodosUseCase()
        }
    }
}