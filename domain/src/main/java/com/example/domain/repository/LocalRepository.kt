package com.example.domain.repository

import com.example.domain.models.user.User
import kotlinx.coroutines.flow.Flow

interface LocalRepository {

    suspend fun insertUsersToDb(users: List<User>): Result<Unit>

    suspend fun getAllUsersFromDb(): Result<List<User>>

    suspend fun subscribeToChangesDb(): Result<Flow<List<User>>>

    suspend fun deleteAllUsersFromDb(): Result<Unit>

}