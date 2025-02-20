package com.example.domain.repository

import com.example.domain.models.user.User

interface RemoteRepository {

    suspend fun getUsers(): Result<List<User>>

}