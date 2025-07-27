package br.com.domotest.domain

import br.com.domotest.repository.TodoRepository

class DeleteAllTodosUseCaseImpl(
    private val todoRepository: TodoRepository
): DeleteAllTodosUseCase {
    override suspend fun invoke() {
        todoRepository.deleteAllTodos()
    }
}