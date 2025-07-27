package br.com.domotest.datasource

import br.com.domotest.model.TodoModel
import br.com.domotest.model.toTodoModel
import br.com.domotest.network.RetrofitApi

class TodoRemoteDatasourceImpl(
    private val api: RetrofitApi
): TodoRemoteDatasource {
    override suspend fun getTodoList(): List<TodoModel> {
        return api.getTodoList()?.map {
            it.toTodoModel()
        } ?: emptyList()
    }
}