package com.example.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.model.ProductModel

@Dao
interface ProductDAO {

    @Query("SELECT * FROM ProductModel")
    fun getProducts(): List<ProductModel>

    @Insert
    fun addProduct(vararg productModel: ProductModel)
}