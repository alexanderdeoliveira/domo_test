package br.com.domotest.repository

interface LoginRepository {
    suspend fun saveUserId(userId: String)
    suspend fun getUserId(): String?
}