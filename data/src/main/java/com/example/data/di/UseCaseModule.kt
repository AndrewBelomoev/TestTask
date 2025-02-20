package com.example.data.di

import com.example.domain.usecase.GetUsersUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

internal val useCaseModule = module {
    factoryOf(::GetUsersUseCase)
}