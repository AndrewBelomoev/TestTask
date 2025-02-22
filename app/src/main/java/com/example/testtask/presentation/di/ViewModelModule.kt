package com.example.testtask.presentation.di

import com.example.testtask.presentation.ui.details.UserDetailsViewModel
import com.example.testtask.presentation.ui.users.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::UsersViewModel)
    viewModelOf(::UserDetailsViewModel)
}