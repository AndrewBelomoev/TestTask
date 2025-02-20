package com.example.data.di

import com.example.data.repository.RemoteRepositoryImpl
import com.example.domain.repository.RemoteRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val repositoryModule = module {
    singleOf(::RemoteRepositoryImpl) { bind<RemoteRepository>() }
}
