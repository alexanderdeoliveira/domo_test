package br.com.domotest.repository

import br.com.domotest.datasource.TodoLocalDatasource
import br.com.domotest.datasource.TodoRemoteDatasource
import br.com.domotest.model.TodoModel

class TodoRepositoryImpl(
    private val todoRemoteDataSource: TodoRemoteDatasource,
    private val todoLocalDataSource: TodoLocalDatasource
): TodoRepository {

    override suspend fun getTodo(
        mockEnabled: Boolean,
        position: Int
    ): TodoModel? {
        return if (mockEnabled) {
            todoLocalDataSource.getTodoItem(position)
        } else {
            null
        }
    }

    override suspend fun getTodoList(): List<TodoModel> {
        return todoRemoteDataSource.getTodoList()
    }

    override suspend fun saveTodo(todoModel: TodoModel) {
        when(todoModel) {
            is TodoModel.TodoLocal -> todoLocalDataSource.saveLocalTodo(todoModel)
            is TodoModel.TodoRemote -> todoLocalDataSource.saveRemoteTodo(todoModel)
        }
    }

    override suspend fun deleteAllTodos() {
        todoLocalDataSource.deleteAllTodos()
    }
}