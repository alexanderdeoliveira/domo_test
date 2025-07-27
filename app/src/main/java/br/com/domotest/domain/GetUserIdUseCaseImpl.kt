package br.com.domotest.domain

import br.com.domotest.repository.LoginRepository

class GetUserIdUseCaseImpl(
    private val loginRepository: LoginRepository
): GetUserIdUseCase {
    override suspend fun invoke(): String? {
        return loginRepository.getUserId()
    }
}