package com.example.data.repository

import com.example.data.database.room.UserDao
import com.example.data.models.toDomainModel
import com.example.data.models.toEntityModel
import com.example.domain.models.user.User
import com.example.domain.repository.LocalRepository

internal class LocalRepositoryImpl(private val dao: UserDao) : LocalRepository {

    override suspend fun insertUsersToDb(users: List<User>) = kotlin.runCatching {
        dao.insertUsers(users = users.map { it.toEntityModel() })
    }

    override suspend fun getAllUsersFromDb() = kotlin.runCatching {
        dao.getAllUsers()
    }.map {
        it.map { userEntity ->
            userEntity.toDomainModel()
        }
    }

    override suspend fun deleteAllUsersFromDb() = kotlin.runCatching {
        dao.deleteAllUsers()
    }

}