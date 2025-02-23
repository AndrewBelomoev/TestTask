package com.example.domain.usecase

import com.example.domain.models.user.User
import com.example.domain.repository.LocalRepository

class GetUsersFromDbUseCase(private val localRepository: LocalRepository) {

    suspend operator fun invoke(): Result<List<User>> {
        return localRepository.getAllUsersFromDb()
    }
}