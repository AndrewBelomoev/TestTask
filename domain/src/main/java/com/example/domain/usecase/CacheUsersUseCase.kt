package com.example.domain.usecase

import com.example.domain.models.user.User
import com.example.domain.repository.LocalRepository

class CacheUsersUseCase(private val localRepository: LocalRepository) {

    suspend operator fun invoke(users: List<User>): Result<Unit> {
        return localRepository.insertUsersToDb(users)
    }
}