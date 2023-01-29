package com.example.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.myapplication.model.UserModel

@Dao
interface UserDAO {

    @Insert
    fun registerUser(user: UserModel)
}