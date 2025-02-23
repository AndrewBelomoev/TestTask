package com.example.data.api

import com.example.data.models.UserDTO
import retrofit2.http.GET

internal interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<UserDTO>

}