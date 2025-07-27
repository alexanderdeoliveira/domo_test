package br.com.domotest.network

import br.com.domotest.model.TodoResponse
import retrofit2.http.GET

interface RetrofitApi {
    @GET("todos")
    suspend fun getTodoList(): List<TodoResponse>?
}