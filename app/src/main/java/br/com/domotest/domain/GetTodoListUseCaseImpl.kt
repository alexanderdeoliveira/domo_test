package br.com.domotest.domain

import br.com.domotest.model.TodoModel
import br.com.domotest.repository.TodoRepository

class GetTodoListUseCaseImpl(
    private val todoRepository: TodoRepository
): GetTodoListUseCase {
    override suspend fun invoke(): List<TodoModel> {
        return todoRepository.getTodoList()
    }
}