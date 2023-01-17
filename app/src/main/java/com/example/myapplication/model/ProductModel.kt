package com.example.myapplication.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Entity
@Parcelize
data class ProductModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val url: String,
    val name: String,
    val description: String,
    val value: BigDecimal
) : Parcelable

fun ProductModel.isValid(): Boolean {
    return name.isNotEmpty() && description.isNotEmpty() && value.toString().isNotEmpty()
}