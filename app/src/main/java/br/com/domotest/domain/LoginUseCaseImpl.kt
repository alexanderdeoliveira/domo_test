package br.com.domotest.domain

import br.com.domotest.repository.LoginRepository

class LoginUseCaseImpl(
    private val loginRepository: LoginRepository
): LoginUseCase {
    override suspend fun invoke(userId: String) {
        loginRepository.login(userId)
    }
}