package com.example.myapplication.model

import java.math.BigDecimal

data class ProductModel(
    val url: String,
    val name: String,
    val description: String,
    val value: BigDecimal
)

fun ProductModel.isValid(): Boolean {
    return name.isNotEmpty() && description.isNotEmpty() && value.toString().isNotEmpty()
}