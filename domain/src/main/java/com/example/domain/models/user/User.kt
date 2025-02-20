package com.example.domain.models.user

data class User(
    val id: Int? = null,
    val name: String? = null,
    val username: String? = null,
    val email: String? = null,
    val address: Address? = null,
    val phone: String? = null,
    val website: String? = null,
    val company: Company? = null
)

data class Address(
    val street: String? = null,
    val suite: String? = null,
    val city: String? = null,
    val zipcode: String? = null,
    val geo: Geo? = null
)

data class Geo(
    val lat: String? = null,
    val lng: String? = null
)

data class Company(
    val name: String? = null,
    val catchPhrase: String? = null,
    val bs: String? = null
)
