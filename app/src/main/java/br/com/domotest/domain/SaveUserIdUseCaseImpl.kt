package br.com.domotest.domain

import br.com.domotest.repository.LoginRepository

class SaveUserIdUseCaseImpl(
    private val loginRepository: LoginRepository
): SaveUserIdUseCase {
    override suspend fun invoke(userId: String) {
        loginRepository.saveUserId(userId)
    }
}