package br.com.domotest.model

sealed class TodoModel {
    data class TodoLocal(
        val message: String
    ): TodoModel()
    data class TodoRemote(
        val message: String,
        val route: String
    ): TodoModel()
}