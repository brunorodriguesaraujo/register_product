package com.example.myapplication.model

data class LoginModel(
    val username: String = "",
    val password: String = ""
)

fun LoginModel.isValid(): Boolean {
    return username.isNotBlank() && password.isNotBlank()
}