package com.example.domain.usecase

import com.example.domain.models.user.User
import com.example.domain.repository.LocalRepository
import kotlinx.coroutines.flow.Flow

class SubscribeToChangesDatabaseUseCase(private val localRepository: LocalRepository) {

    suspend operator fun invoke(): Result<Flow<List<User>>> {
        return localRepository.subscribeToChangesDb()
    }
}