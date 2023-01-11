package com.example.myapplication.extension

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

fun BigDecimal.toFormatCurrencyBrazilian(): String {
    return NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(this)
}