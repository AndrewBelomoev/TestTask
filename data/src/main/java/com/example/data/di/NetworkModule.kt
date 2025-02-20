package com.example.data.di

import com.example.data.api.ApiService
import okhttp3.OkHttpClient
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

internal val networkModule = module {

    single() {
        OkHttpClient
            .Builder()
            .build()
    }

    single(qualifier("API")) {
        val httpClient = OkHttpClient.Builder()
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    single {
        get<Retrofit>(qualifier("API")).create<ApiService>()
    }

}

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"