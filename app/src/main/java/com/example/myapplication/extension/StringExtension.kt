package com.example.myapplication.extension

import java.math.BigDecimal
import java.text.NumberFormat

fun BigDecimal.toFormatCurrencyBrazilian(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}