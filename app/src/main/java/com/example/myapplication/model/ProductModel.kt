package com.example.myapplication.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class ProductModel(
    val url: String,
    val name: String,
    val description: String,
    val value: BigDecimal
) : Parcelable

fun ProductModel.isValid(): Boolean {
    return name.isNotEmpty() && description.isNotEmpty() && value.toString().isNotEmpty()
}