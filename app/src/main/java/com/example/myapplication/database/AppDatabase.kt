package com.example.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.database.converter.Converter
import com.example.myapplication.database.dao.ProductDAO
import com.example.myapplication.database.dao.UserDAO
import com.example.myapplication.model.ProductModel
import com.example.myapplication.model.UserModel

@Database(entities = [ProductModel::class, UserModel::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDAO

    abstract fun userDao(): UserDAO

    companion object {
        private const val DATABASE_NAME = "myapplication.db"

        @Volatile
        private lateinit var dbInstance: AppDatabase
        fun instance(context: Context): AppDatabase {
            return if (::dbInstance.isInitialized) {
                dbInstance
            } else Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DATABASE_NAME
            )
                .build()
                .also { dbInstance = it }
        }
    }
}