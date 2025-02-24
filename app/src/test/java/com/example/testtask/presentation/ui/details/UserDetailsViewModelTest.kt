package com.example.testtask.presentation.ui.details

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class UserDetailsViewModelTest {

    private lateinit var viewModel: UserDetailsViewModel
    private val testDispatcher = UnconfinedTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        viewModel = UserDetailsViewModel()
    }

    @Test
    fun `test setting user details`() = testScope.runTest {
        // Given
        val id = 1L
        val name = "Test Name"
        val email = "testMail@gmail.com"
        val phone = "1234567890"
        val city = "City"

        // When
        viewModel.setUserDetails(id, name, email, phone, city)

        // Then
        val user = viewModel.userDetails.first()
        assertEquals(id, user?.id)
        assertEquals(name, user?.name)
        assertEquals(email, user?.email)
        assertEquals(phone, user?.phone)
        assertEquals(city, user?.address?.city)
    }
}