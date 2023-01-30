package com.example.myapplication.extension

import com.google.android.material.textfield.TextInputLayout

private const val EMPTY_FIELD = "Campo vazio"
fun TextInputLayout.setErrorEmptyField(text: String) {
    error = if (text.isBlank()) EMPTY_FIELD else null
}