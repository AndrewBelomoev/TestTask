package com.example.testtask.presentation.ui.details

import androidx.lifecycle.ViewModel
import com.example.domain.models.user.Address
import com.example.domain.models.user.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserDetailsViewModel : ViewModel() {
    private val _userDetails = MutableStateFlow<User?>(null)
    val userDetails: StateFlow<User?> get() = _userDetails

    fun setUserDetails(name: String, email: String, phone: String, city: String) {
        val user = User(
            name = name,
            email = email,
            phone = phone,
            address = Address(city = city)
        )
        _userDetails.value = user
    }
}