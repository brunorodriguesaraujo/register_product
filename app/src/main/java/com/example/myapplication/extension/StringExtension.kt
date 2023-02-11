package com.example.myapplication.extension

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*

private val LOCALE_BR = Locale("pt", "BR")

fun BigDecimal.toFormatCurrencyBrazilian(): String {
    return NumberFormat.getCurrencyInstance(LOCALE_BR).format(this)
}