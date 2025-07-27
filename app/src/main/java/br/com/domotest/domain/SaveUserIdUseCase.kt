package br.com.domotest.domain

interface SaveUserIdUseCase {
    suspend operator fun invoke(
        userId: String
    )
}