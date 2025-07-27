package br.com.domotest.datasource

import br.com.domotest.database.TodoDao
import br.com.domotest.database.TodoEntity
import br.com.domotest.model.TodoModel

class TodoLocalDatasourceImpl(
    private val todoDao: TodoDao
): TodoLocalDatasource {

    private val mockTodoLocalList: List<TodoModel.TodoLocal> = listOf(
        TodoModel.TodoLocal(message="delectus aut autem (mock)"),
        TodoModel.TodoLocal(message="quis ut nam facilis et officia qui (mock)"),
        TodoModel.TodoLocal(message="fugiat veniam minus (mock)"),
        TodoModel.TodoLocal(message="et porro tempora (mock)"),
        TodoModel.TodoLocal(message="laboriosam mollitia et enim quasi adipisci quia provident illum (mock)"),
        TodoModel.TodoLocal(message="qui ullam ratione quibusdam voluptatem quia omnis (mock)"),
        TodoModel.TodoLocal(message="illo expedita consequatur quia in (mock)"),
        TodoModel.TodoLocal(message="quo adipisci enim quam ut ab (mock)"),
        TodoModel.TodoLocal(message="molestiae perspiciatis ipsa (mock)"),
        TodoModel.TodoLocal(message="illo est ratione doloremque quia maiores aut (mock)"),
        TodoModel.TodoLocal(message="vero rerum temporibus dolor (mock)"),
        TodoModel.TodoLocal(message="ipsa repellendus fugit nisi (mock)"),
        TodoModel.TodoLocal(message="et doloremque nulla (mock)"),
        TodoModel.TodoLocal(message="repellendus sunt dolores architecto voluptatum (mock)"),
        TodoModel.TodoLocal(message="ab voluptatum amet voluptas (mock)"),
        TodoModel.TodoLocal(message="accusamus eos facilis sint et aut voluptatem (mock)"),
        TodoModel.TodoLocal(message="quo laboriosam deleniti aut qui (mock)"),
        TodoModel.TodoLocal(message="dolorum est consequatur ea mollitia in culpa (mock)"),
        TodoModel.TodoLocal(message="molestiae ipsa aut voluptatibus pariatur dolor nihil (mock)"),
        TodoModel.TodoLocal(message="ullam nobis libero sapiente ad optio sint (mock)"),
        TodoModel.TodoLocal(message="suscipit repellat esse quibusdam voluptatem incidunt (mock)"),
        TodoModel.TodoLocal(message="distinctio vitae autem nihil ut molestias quo (mock)"),
        TodoModel.TodoLocal(message="et itaque necessitatibus maxime molestiae qui quas velit (mock)"),
        TodoModel.TodoLocal(message="adipisci non ad dicta qui amet quaerat doloribus ea (mock)"),
        TodoModel.TodoLocal(message="voluptas quo tenetur perspiciatis explicabo natus (mock)"),
        TodoModel.TodoLocal(message="aliquam aut quasi (mock)"),
        TodoModel.TodoLocal(message="veritatis pariatur delectus (mock)"),
        TodoModel.TodoLocal(message="nesciunt totam sit blanditiis sit (mock)"),
        TodoModel.TodoLocal(message="laborum aut in quam (mock)"),
        TodoModel.TodoLocal(message="nemo perspiciatis repellat ut dolor libero commodi blanditiis omnis (mock)"),
        TodoModel.TodoLocal(message="repudiandae totam in est sint facere fuga (mock)"),
        TodoModel.TodoLocal(message="earum doloribus ea doloremque quis (mock)"),
        TodoModel.TodoLocal(message="sint sit aut vero (mock)"),
        TodoModel.TodoLocal(message="porro aut necessitatibus eaque distinctio (mock)"),
        TodoModel.TodoLocal(message="repellendus veritatis molestias dicta incidunt (mock)"),
        TodoModel.TodoLocal(message="excepturi deleniti adipisci voluptatem et neque optio illum ad (mock)"),
        TodoModel.TodoLocal(message="sunt cum tempora (mock)"),
        TodoModel.TodoLocal(message="totam quia non (mock)"),
        TodoModel.TodoLocal(message="doloremque quibusdam asperiores libero corrupti illum qui omnis (mock)"),
        TodoModel.TodoLocal(message="totam atque quo nesciunt (mock)"),
        TodoModel.TodoLocal(message="aliquid amet impedit consequatur aspernatur placeat eaque fugiat suscipit (mock)"),
        TodoModel.TodoLocal(message="rerum perferendis error quia ut eveniet (mock)"),
        TodoModel.TodoLocal(message="tempore ut sint quis recusandae (mock)"),
        TodoModel.TodoLocal(message="cum debitis quis accusamus doloremque ipsa natus sapiente omnis (mock)"),
        TodoModel.TodoLocal(message="velit soluta adipisci molestias reiciendis harum (mock)"),
        TodoModel.TodoLocal(message="vel voluptatem repellat nihil placeat corporis (mock)"),
        TodoModel.TodoLocal(message="nam qui rerum fugiat accusamus (mock)"),
        TodoModel.TodoLocal(message="sit reprehenderit omnis quia (mock)"),
        TodoModel.TodoLocal(message="ut necessitatibus aut maiores debitis officia blanditiis velit et (mock)"),
        TodoModel.TodoLocal(message="cupiditate necessitatibus ullam aut quis dolor voluptate (mock)"),
        TodoModel.TodoLocal(message="distinctio exercitationem ab doloribus (mock)"),
        TodoModel.TodoLocal(message="nesciunt dolorum quis recusandae ad pariatur ratione (mock)"),
        TodoModel.TodoLocal(message="qui labore est occaecati recusandae aliquid quam (mock)"),
        TodoModel.TodoLocal(message="quis et est ut voluptate quam dolor (mock)"),
        TodoModel.TodoLocal(message="voluptatum omnis minima qui occaecati provident nulla voluptatem ratione (mock)"),
        TodoModel.TodoLocal(message="deleniti ea temporibus enim (mock)"),
        TodoModel.TodoLocal(message="pariatur et magnam ea doloribus similique voluptatem rerum quia (mock)"),
        TodoModel.TodoLocal(message="est dicta totam qui explicabo doloribus qui dignissimos (mock)"),
        TodoModel.TodoLocal(message="perspiciatis velit id laborum placeat iusto et aliquam odio (mock)"),
        TodoModel.TodoLocal(message="et sequi qui architecto ut adipisci (mock)"),
        TodoModel.TodoLocal(message="odit optio omnis qui sunt (mock)"),
        TodoModel.TodoLocal(message="et placeat et tempore aspernatur sint numquam (mock)"),
        TodoModel.TodoLocal(message="doloremque aut dolores quidem fuga qui nulla (mock)"),
        TodoModel.TodoLocal(message="voluptas consequatur qui ut quia magnam nemo esse (mock)"),
        TodoModel.TodoLocal(message="fugiat pariatur ratione ut asperiores necessitatibus magni (mock)"),
        TodoModel.TodoLocal(message="rerum eum molestias autem voluptatum sit optio (mock)"),
        TodoModel.TodoLocal(message="quia voluptatibus voluptatem quos similique maiores repellat (mock)"),
        TodoModel.TodoLocal(message="aut id perspiciatis voluptatem iusto (mock)"),
        TodoModel.TodoLocal(message="doloribus sint dolorum ab adipisci itaque dignissimos aliquam suscipit (mock)"),
        TodoModel.TodoLocal(message="ut sequi accusantium et mollitia delectus sunt (mock)"),
        TodoModel.TodoLocal(message="aut velit saepe ullam (mock)"),
        TodoModel.TodoLocal(message="praesentium facilis facere quis harum voluptatibus voluptatem eum (mock)"),
        TodoModel.TodoLocal(message="sint amet quia totam corporis qui exercitationem commodi (mock)"),
        TodoModel.TodoLocal(message="expedita tempore nobis eveniet laborum maiores (mock)"),
        TodoModel.TodoLocal(message="occaecati adipisci est possimus totam (mock)"),
        TodoModel.TodoLocal(message="sequi dolorem sed (mock)"),
        TodoModel.TodoLocal(message="maiores aut nesciunt delectus exercitationem vel assumenda eligendi at (mock)"),
        TodoModel.TodoLocal(message="reiciendis est magnam amet nemo iste recusandae impedit quaerat (mock)"),
        TodoModel.TodoLocal(message="eum ipsa maxime ut (mock)"),
        TodoModel.TodoLocal(message="tempore molestias dolores rerum sequi voluptates ipsum consequatur (mock)"),
        TodoModel.TodoLocal(message="suscipit qui totam (mock)"))

    override suspend fun saveLocalTodo(todo: TodoModel.TodoLocal) {
        insertTodo(
            TodoEntity(
                message = todo.message
            )
        )
    }

    override suspend fun saveRemoteTodo(todo: TodoModel.TodoRemote) {
        insertTodo(
            TodoEntity(
                message = todo.message,
                route = todo.route
            )
        )
    }

    override suspend fun deleteAllTodos() {
        todoDao.deleteAllTodos()
    }

    private suspend fun insertTodo(todoEntity: TodoEntity) {
        todoDao.insertAndTrim(todoEntity)
    }

    override suspend fun getTodoItem(position: Int): TodoModel? {
        return mockTodoLocalList.getOrNull(position)
    }
}