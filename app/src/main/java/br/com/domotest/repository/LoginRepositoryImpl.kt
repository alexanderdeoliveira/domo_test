package br.com.domotest.repository

import br.com.domotest.datasource.LoginLocalDatasource

class LoginRepositoryImpl(
    private val loginLocalDataSource: LoginLocalDatasource
): LoginRepository {
    override suspend fun saveUserId(userId: String) {
        loginLocalDataSource.saveUserId(userId)
    }

    override suspend fun getUserId(): String? {
        return loginLocalDataSource.getUserId()
    }
}