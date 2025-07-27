package br.com.domotest.domain

import br.com.domotest.repository.LoginRepository
import br.com.domotest.repository.TodoRepository

class LogoutUseCaseImpl(
    private val loginRepository: LoginRepository,
    private val todoRepository: TodoRepository
): LogoutUseCase {
    override suspend fun invoke() {
        todoRepository.deleteAllTodos()
        loginRepository.logout()
    }
}