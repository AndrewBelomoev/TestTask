package com.example.data.models

import com.example.domain.models.user.Address
import com.example.domain.models.user.Company
import com.example.domain.models.user.Geo
import com.example.domain.models.user.User

internal fun User.toDTOModel(): UserDTO {
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

internal fun UserDTO.toDomainModel(): User {
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

internal fun User.toEntityModel(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        username = username,
        email = email,
        address = address?.toEntityModel(),
        phone = phone,
        website = website,
        company = company?.toEntityModel()
    )
}

internal fun UserEntity.toDomainModel(): User {
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

internal fun Address.toDTOModel(): AddressDTO {
    return AddressDTO(
        street = street,
        suite = suite,
        city = city,
        zipcode = zipcode,
        geo = geo?.toDTOModel()
    )
}

internal fun Geo.toDTOModel(): GeoDTO {
    return GeoDTO(
        lat = lat,
        lng = lng
    )
}

internal fun Company.toDTOModel(): CompanyDTO {
    return CompanyDTO(
        name = name,
        catchPhrase = catchPhrase,
        bs = bs
    )
}

internal fun AddressDTO.toDomainModel(): Address {
    return Address(
        street = street,
        suite = suite,
        city = city,
        zipcode = zipcode,
        geo = geo?.toDomainModel()
    )
}

internal fun GeoDTO.toDomainModel(): Geo {
    return Geo(
        lat = lat,
        lng = lng
    )
}

internal fun CompanyDTO.toDomainModel(): Company {
    return Company(
        name = name,
        catchPhrase = catchPhrase,
        bs = bs
    )
}

internal fun Address.toEntityModel(): AddressEntity {
    return AddressEntity(
        street = street,
        suite = suite,
        city = city,
        zipcode = zipcode,
        geo = geo?.toEntityModel()
    )
}

internal fun Geo.toEntityModel(): GeoEntity {
    return GeoEntity(
        lat = lat,
        lng = lng
    )
}

internal fun Company.toEntityModel(): CompanyEntity {
    return CompanyEntity(
        name = name,
        catchPhrase = catchPhrase,
        bs = bs
    )
}

internal fun AddressEntity.toDomainModel(): Address {
    return Address(
        street = street,
        suite = suite,
        city = city,
        zipcode = zipcode,
        geo = geo?.toDomainModel()
    )
}

internal fun GeoEntity.toDomainModel(): Geo {
    return Geo(
        lat = lat,
        lng = lng
    )
}

internal fun CompanyEntity.toDomainModel(): Company {
    return Company(
        name = name,
        catchPhrase = catchPhrase,
        bs = bs
    )
}
