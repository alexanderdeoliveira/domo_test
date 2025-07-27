package br.com.domotest.domain

interface GenerateUUIDUseCase {
    suspend operator fun invoke(
        onGenerateFinished: (generatedUUID: String?) -> Unit
    )
}