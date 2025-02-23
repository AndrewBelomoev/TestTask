package com.example.data.models

internal data class UserDTO(
    val id: Int? = null,
    val name: String? = null,
    val username: String? = null,
    val email: String? = null,
    val address: AddressDTO? = null,
    val phone: String? = null,
    val website: String? = null,
    val company: CompanyDTO? = null
)

internal data class AddressDTO(
    val street: String? = null,
    val suite: String? = null,
    val city: String? = null,
    val zipcode: String? = null,
    val geo: GeoDTO? = null
)

internal data class GeoDTO(
    val lat: String? = null,
    val lng: String? = null
)

internal data class CompanyDTO(
    val name: String? = null,
    val catchPhrase: String? = null,
    val bs: String? = null
)
