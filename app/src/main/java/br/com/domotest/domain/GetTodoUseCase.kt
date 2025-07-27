package br.com.domotest.domain

import br.com.domotest.model.TodoModel

interface GetTodoUseCase {
    suspend operator fun invoke(mockEnabled: Boolean, position: Int): TodoModel?
}