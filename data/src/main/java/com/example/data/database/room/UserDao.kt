package com.example.data.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.models.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface UserDao {

    @Query("SELECT * FROM userentity")
    suspend fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM userentity")
    fun subscribeChanges(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<UserEntity>)

    @Query("DELETE FROM userentity")
    suspend fun deleteAllUsers()

}