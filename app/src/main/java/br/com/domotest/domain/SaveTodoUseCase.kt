package br.com.domotest.domain

import br.com.domotest.model.TodoModel

interface SaveTodoUseCase {
    suspend operator fun invoke(todoModel: TodoModel)
}