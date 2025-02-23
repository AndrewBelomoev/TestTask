package com.example.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String? = null,
    val username: String? = null,
    val email: String? = null,
    val address: AddressEntity? = null,
    val phone: String? = null,
    val website: String? = null,
    val company: CompanyEntity? = null
)

internal data class AddressEntity(
    val street: String? = null,
    val suite: String? = null,
    val city: String? = null,
    val zipcode: String? = null,
    val geo: GeoEntity? = null
)

internal data class GeoEntity(
    val lat: String? = null,
    val lng: String? = null
)

internal data class CompanyEntity(
    val name: String? = null,
    val catchPhrase: String? = null,
    val bs: String? = null
)