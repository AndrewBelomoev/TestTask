package com.example.data.di

import androidx.room.Room
import com.example.data.database.room.AppDatabase
import org.koin.dsl.module

internal val databaseModule = module {
    single {
        Room
            .databaseBuilder(
                get(),
                AppDatabase::class.java,
                "cached_users.db"
            )
            .build()
    }

    single { get<AppDatabase>().userDao() }
}