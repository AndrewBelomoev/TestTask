package com.example.data.repository

import com.example.data.database.room.UserDao
import com.example.data.models.UserEntity
import com.example.data.models.toDomainModel
import com.example.data.models.toEntityModel
import com.example.domain.models.user.User
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

internal class LocalRepositoryImplTest {

    private lateinit var userDao: UserDao
    private lateinit var localRepository: LocalRepositoryImpl

    @Before
    fun setUp() {
        userDao = mock()
        localRepository = LocalRepositoryImpl(userDao)
    }

    @Test
    fun `insertUsersToDb should insert users successfully`() = runBlocking {
        // Arrange
        val users = listOf(User(id = 1, name = "User1"))
        whenever(userDao.insertUsers(users.map { it.toEntityModel() })).thenReturn(Unit)

        // Act
        val result = localRepository.insertUsersToDb(users)

        // Assert
        assertEquals(Result.success(Unit), result)
    }

    @Test
    fun `getAllUsersFromDb should return list of users`() = runBlocking {
        // Arrange
        val userEntities = listOf(UserEntity(id = 1, name = "User1"))
        val users = userEntities.map { it.toDomainModel() }
        whenever(userDao.getAllUsers()).thenReturn(userEntities)

        // Act
        val result = localRepository.getAllUsersFromDb()

        // Assert
        assertEquals(Result.success(users), result)
    }


    @Test
    fun `subscribeToChangesDb should return flow of users`() = runBlocking {
        // Arrange
        val userEntities = listOf(UserEntity(id = 1, name = "User1"))
        val users = userEntities.map { it.toDomainModel() }
        val userFlow = flowOf(userEntities)
        whenever(userDao.subscribeChanges()).thenReturn(userFlow)

        // Act
        val result = localRepository.subscribeToChangesDb()

        // Assert
        val collectedUsers = result.getOrThrow().first()
        assertEquals(users, collectedUsers)
    }


    @Test
    fun `deleteAllUsersFromDb should delete all users successfully`() = runBlocking {
        // Arrange
        whenever(userDao.deleteAllUsers()).thenReturn(Unit)

        // Act
        val result = localRepository.deleteAllUsersFromDb()

        // Assert
        assertEquals(Result.success(Unit), result)
    }
}