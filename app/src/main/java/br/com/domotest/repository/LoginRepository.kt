package br.com.domotest.repository

interface LoginRepository {
    suspend fun login(userId: String)
    suspend fun getUserId(): String?
    suspend fun logout()
}