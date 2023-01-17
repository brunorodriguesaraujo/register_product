package com.example.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.database.converter.Converter
import com.example.myapplication.database.dao.ProductDAO
import com.example.myapplication.model.ProductModel

@Database(entities = [ProductModel::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDAO
}