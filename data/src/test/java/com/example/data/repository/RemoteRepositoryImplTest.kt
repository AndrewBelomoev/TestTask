package com.example.data.repository

import com.example.data.api.ApiService
import com.example.data.models.UserDTO
import com.example.data.models.toDomainModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

internal class RemoteRepositoryImplTest {

    private lateinit var apiService: ApiService
    private lateinit var remoteRepository: RemoteRepositoryImpl

    @Before
    fun setUp() {
        apiService = mock()
        remoteRepository = RemoteRepositoryImpl(apiService)
    }

    @Test
    fun `getUsers should return mapped users`() = runBlocking<Unit> {
        // Arrange
        val apiUsers = listOf(
            UserDTO(id = 1, name = "User1"),
            UserDTO(id = 2, name = "User2")
        )
        val domainUsers = apiUsers.map { it.toDomainModel() }
        whenever(apiService.getUsers()).thenReturn(apiUsers)

        // Act
        val result = remoteRepository.getUsers()

        // Assert
        assertEquals(Result.success(domainUsers), result)
        verify(apiService).getUsers()
    }

    @Test
    fun `getUsers should return failure result on error`() = runBlocking<Unit> {
        // Arrange
        val exception = RuntimeException("Network error")
        whenever(apiService.getUsers()).thenThrow(exception)

        // Act
        val result = remoteRepository.getUsers()

        // Assert
        assertEquals(Result.failure(exception), result)
        verify(apiService).getUsers()
    }
}