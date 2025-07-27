package br.com.domotest.domain

import br.com.domotest.model.TodoModel
import br.com.domotest.repository.TodoRepository

class GetTodoUseCaseImpl(
    private val todoRepository: TodoRepository
): GetTodoUseCase {
    override suspend fun invoke(
        mockEnabled: Boolean,
        position: Int
    ): TodoModel? {
        return todoRepository.getTodo(mockEnabled, position)
    }
}