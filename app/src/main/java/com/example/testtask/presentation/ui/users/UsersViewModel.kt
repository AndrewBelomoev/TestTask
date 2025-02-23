package com.example.testtask.presentation.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.user.User
import com.example.domain.usecase.CacheUsersUseCase
import com.example.domain.usecase.GetUsersFromDbUseCase
import com.example.domain.usecase.GetUsersUseCase
import com.example.testtask.presentation.model.LceState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsersViewModel(
    private val getUsersUseCase: GetUsersUseCase,
    private val getUsersFromDbUseCase: GetUsersFromDbUseCase,
    private val cacheUsersUseCase: CacheUsersUseCase
) : ViewModel() {
    private val _usersState = MutableStateFlow<LceState<List<User>>>(LceState.Loading)
    val usersState: StateFlow<LceState<List<User>>> get() = _usersState

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            _usersState.value = LceState.Loading
            delay(300)
            getUsersFromDbUseCase().onSuccess { users ->
                if (users.isNotEmpty()) {
                    _usersState.value = LceState.Content(users)
                }
            }

            val result = getUsersUseCase()
            _usersState.value = result.fold(
                onSuccess = { users ->
                    cacheUsersUseCase(users)
                    LceState.Content(users)
                },
                onFailure = { throwable -> LceState.Error(throwable) }
            )
        }
    }
}
