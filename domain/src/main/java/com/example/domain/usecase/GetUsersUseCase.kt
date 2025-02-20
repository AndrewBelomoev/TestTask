package com.example.domain.usecase

import com.example.domain.models.user.User
import com.example.domain.repository.RemoteRepository

class GetUsersUseCase(private val repository: RemoteRepository) {

    suspend operator fun invoke(): Result<List<User>> {
        return repository.getUsers()
    }

}