package br.com.domotest.datasource

interface LoginLocalDatasource {
    suspend fun saveUserId(userId: String)
    suspend fun getUserId(): String?
}