package br.com.domotest.repository

import br.com.domotest.model.TodoModel

interface TodoRepository {
    suspend fun getTodo(
        mockEnabled: Boolean,
        position: Int
    ): TodoModel?
    suspend fun getTodoList(): List<TodoModel>
    suspend fun saveTodo(todoModel: TodoModel)
    suspend fun deleteAllTodos()
}