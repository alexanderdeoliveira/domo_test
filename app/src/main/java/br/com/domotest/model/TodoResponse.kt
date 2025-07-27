package br.com.domotest.model

import android.os.Parcelable
import br.com.domotest.util.BASE_URL
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodoResponse(
    val userId: Int? = null,
    val id: Int? = null,
    val title: String? = null,
    val completed: Boolean? = null
): Parcelable

fun TodoResponse.toTodoModel(): TodoModel {
    return TodoModel.TodoRemote(
        message = this.title.orEmpty(),
        route = BASE_URL
    )
}