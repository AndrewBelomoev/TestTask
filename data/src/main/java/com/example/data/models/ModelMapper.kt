package com.example.data.models

import com.example.domain.models.user.Address
import com.example.domain.models.user.Company
import com.example.domain.models.user.Geo
import com.example.domain.models.user.User

fun User.toDTOModel(): UserDTO {
    return UserDTO(
        id = id,
        name = name,
        username = username,
        email = email,
        address = address?.toDTOModel(),
        phone = phone,
        website = website,
        company = company?.toDTOModel()
    )
}

fun UserDTO.toDomainModel(): User {
    return User(
        id = id,
        name = name,
        username = username,
        email = email,
        address = address?.toDomainModel(),
        phone = phone,
        website = website,
        company = company?.toDomainModel()
    )
}

fun Address.toDTOModel(): AddressDTO {
    return AddressDTO(
        street = street,
        suite = suite,
        city = city,
        zipcode = zipcode,
        geo = geo?.toDTOModel()
    )
}

fun Geo.toDTOModel(): GeoDTO {
    return GeoDTO(
        lat = lat,
        lng = lng
    )
}

fun Company.toDTOModel(): CompanyDTO {
    return CompanyDTO(
        name = name,
        catchPhrase = catchPhrase,
        bs = bs
    )
}

fun AddressDTO.toDomainModel(): Address {
    return Address(
        street = street,
        suite = suite,
        city = city,
        zipcode = zipcode,
        geo = geo?.toDomainModel()
    )
}

fun GeoDTO.toDomainModel(): Geo {
    return Geo(
        lat = lat,
        lng = lng
    )
}

fun CompanyDTO.toDomainModel(): Company {
    return Company(
        name = name,
        catchPhrase = catchPhrase,
        bs = bs
    )
}