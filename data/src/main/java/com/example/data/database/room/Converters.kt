package com.example.data.database.room

import androidx.room.TypeConverter
import com.example.data.models.AddressEntity
import com.example.data.models.CompanyEntity
import com.example.data.models.GeoEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

internal class Converters {

    @TypeConverter
    fun fromAddress(address: AddressEntity?): String? {
        return Gson().toJson(address)
    }

    @TypeConverter
    fun toAddress(addressString: String?): AddressEntity? {
        return Gson().fromJson(addressString, object : TypeToken<AddressEntity>() {}.type)
    }

    @TypeConverter
    fun fromGeo(geo: GeoEntity?): String? {
        return Gson().toJson(geo)
    }

    @TypeConverter
    fun toGeo(geoString: String?): GeoEntity? {
        return Gson().fromJson(geoString, object : TypeToken<GeoEntity>() {}.type)
    }

    @TypeConverter
    fun fromCompany(company: CompanyEntity?): String? {
        return Gson().toJson(company)
    }

    @TypeConverter
    fun toCompany(companyString: String?): CompanyEntity? {
        return Gson().fromJson(companyString, object : TypeToken<CompanyEntity>() {}.type)
    }
}