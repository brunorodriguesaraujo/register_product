package com.example.myapplication.extension

import com.example.myapplication.R
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.setErrorEmptyField(text: String) {
    isErrorEnabled = text.isBlank()
    error = R.string.empty_field.toString()
}