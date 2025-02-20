package com.example.data.repository

import com.example.data.api.ApiService
import com.example.data.models.toDomainModel
import com.example.domain.repository.RemoteRepository

internal class RemoteRepositoryImpl(private val api: ApiService) : RemoteRepository {

    override suspend fun getUsers() = runCatching {
        api.getUsers()
    }.map { usersList ->
        usersList.map { it.toDomainModel() }
    }

}