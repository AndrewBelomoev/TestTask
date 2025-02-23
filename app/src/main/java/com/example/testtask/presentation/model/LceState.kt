package com.example.testtask.presentation.model

sealed class LceState<out T> {
    data object Loading : LceState<Nothing>()
    data class Content<T>(val value: T) : LceState<T>()
    data class Error(val throwable: Throwable) : LceState<Nothing>()
}