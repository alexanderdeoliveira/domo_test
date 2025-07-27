package br.com.domotest.repository

import br.com.domotest.datasource.LoginLocalDatasource

class LoginRepositoryImpl(
    private val loginLocalDataSource: LoginLocalDatasource
): LoginRepository {
    override suspend fun login(userId: String) {
        loginLocalDataSource.saveUserId(userId)
    }

    override suspend fun getUserId(): String? {
        return loginLocalDataSource.getUserId()
    }

    override suspend fun logout() {
        loginLocalDataSource.clearUserData()
    }
}