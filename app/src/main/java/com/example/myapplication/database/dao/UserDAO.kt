package com.example.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.model.UserModel

@Dao
interface UserDAO {

    @Insert
    suspend fun registerUser(user: UserModel)

    @Query("SELECT * FROM UserModel WHERE username =:username AND password =:password")
    suspend fun authUser(username: String, password: String): UserModel?
}