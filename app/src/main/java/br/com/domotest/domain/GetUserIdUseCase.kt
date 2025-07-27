package br.com.domotest.domain

interface GetUserIdUseCase {
    suspend operator fun invoke(): String?
}