package br.com.domotest.datasource

import br.com.domotest.model.TodoModel

interface TodoRemoteDatasource {
    suspend fun getTodoList(): List<TodoModel>
}