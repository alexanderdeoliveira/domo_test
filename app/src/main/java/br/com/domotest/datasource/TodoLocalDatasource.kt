package br.com.domotest.datasource

import br.com.domotest.model.TodoModel

interface TodoLocalDatasource {
    suspend fun saveLocalTodo(todo: TodoModel.TodoLocal)
    suspend fun saveRemoteTodo(todo: TodoModel.TodoRemote)
    suspend fun deleteAllTodos()
    suspend fun getTodoItem(position: Int): TodoModel?
}