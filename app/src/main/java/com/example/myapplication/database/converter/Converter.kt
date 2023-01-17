package com.example.myapplication.database.converter

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converter {

    @TypeConverter
    fun doubleToBigdecimal(value: Double?): BigDecimal {
        return value?.let { BigDecimal(it.toString()) } ?: BigDecimal.ZERO
    }

    @TypeConverter
    fun bigDecimalToDouble(value: BigDecimal?): Double? {
        return value?.toDouble()
    }
}