package com.example.myapplication.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class UserModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val username: String = "",
    val name: String = "",
    val password: String = ""
) : Parcelable

fun UserModel.isValid(): Boolean {
    return username.isNotBlank() && name.isNotBlank() && password.isNotBlank()
}