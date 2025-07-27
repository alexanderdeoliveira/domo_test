package br.com.domotest.domain

interface LoginUseCase {
    suspend operator fun invoke(
        userId: String
    )
}