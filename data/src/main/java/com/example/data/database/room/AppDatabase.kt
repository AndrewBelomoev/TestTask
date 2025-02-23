package com.example.data.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.models.UserEntity

@Database(entities = [UserEntity::class], version = 1)
@TypeConverters(Converters::class)
internal abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}