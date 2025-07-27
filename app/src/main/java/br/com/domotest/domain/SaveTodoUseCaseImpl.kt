package br.com.domotest.domain

import br.com.domotest.model.TodoModel
import br.com.domotest.repository.TodoRepository

class SaveTodoUseCaseImpl(
    private val todoRepository: TodoRepository
): SaveTodoUseCase {
    override suspend fun invoke(todoModel: TodoModel) {
        todoRepository.saveTodo(todoModel)
    }
}