package com.example.testtask.presentation.ui.users

import com.example.domain.models.user.User
import com.example.domain.usecase.CacheUsersUseCase
import com.example.domain.usecase.GetUsersFromDbUseCase
import com.example.domain.usecase.GetUsersUseCase
import com.example.testtask.presentation.model.LceState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class UsersViewModelTest {

    private lateinit var viewModel: UsersViewModel
    private val getUsersUseCase: GetUsersUseCase = mock()
    private val getUsersFromDbUseCase: GetUsersFromDbUseCase = mock()
    private val cacheUsersUseCase: CacheUsersUseCase = mock()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = UsersViewModel(getUsersUseCase, getUsersFromDbUseCase, cacheUsersUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchUsers should emit Loading state initially`() = runTest {
        // Arrange
        `when`(getUsersFromDbUseCase()).thenReturn(Result.success(emptyList()))
        `when`(getUsersUseCase()).thenReturn(Result.success(emptyList()))

        // Act
        viewModel.fetchUsers()

        // Assert
        assertEquals(LceState.Loading, viewModel.usersState.value)
    }

    @Test
    fun `fetchUsers should emit Content state with users from API if DB is empty`() = runTest {
        // Arrange
        val usersFromApi = listOf(User(1, "User1"), User(2, "User2"))
        `when`(getUsersFromDbUseCase()).thenReturn(Result.success(emptyList()))
        `when`(getUsersUseCase()).thenReturn(Result.success(usersFromApi))

        // Act
        viewModel.fetchUsers()
        advanceUntilIdle()

        // Assert
        assertEquals(LceState.Content(usersFromApi), viewModel.usersState.value)
    }

    @Test
    fun `fetchUsers should emit Error state if API call fails`() = runTest {
        // Arrange
        val throwable = Throwable("API Error")
        `when`(getUsersFromDbUseCase()).thenReturn(Result.success(emptyList()))
        `when`(getUsersUseCase()).thenReturn(Result.failure(throwable))

        // Act
        viewModel.fetchUsers()
        advanceUntilIdle()

        // Assert
        assertEquals(LceState.Error(throwable), viewModel.usersState.value)
    }
}