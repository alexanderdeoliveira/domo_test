package br.com.domotest.domain

import br.com.domotest.model.TodoModel

interface GetTodoListUseCase {
    suspend operator fun invoke(): List<TodoModel>
}