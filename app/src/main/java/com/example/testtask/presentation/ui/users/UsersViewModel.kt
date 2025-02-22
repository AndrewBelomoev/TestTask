package com.example.testtask.presentation.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.user.User
import com.example.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsersViewModel(private val getUsersUseCase: GetUsersUseCase) : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> get() = _users


    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            val result = getUsersUseCase()
            if (result.isSuccess) {
                _users.value = result.getOrNull() ?: emptyList()
            } else {
                // Error Handling
            }
        }
    }
}