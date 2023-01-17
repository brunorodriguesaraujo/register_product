package com.example.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.database.converter.Converter
import com.example.myapplication.database.dao.ProductDAO
import com.example.myapplication.model.ProductModel

@Database(entities = [ProductModel::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDAO

    companion object {
        fun instance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "myapplication.db"
            )
                .allowMainThreadQueries()
                .build()
        }
    }
}