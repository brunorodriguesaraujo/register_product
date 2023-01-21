package com.example.myapplication.database.dao

import androidx.room.*
import com.example.myapplication.model.ProductModel

@Dao
interface ProductDAO {

    @Query("SELECT * FROM ProductModel")
    fun getProducts(): List<ProductModel>

    @Query("SELECT * FROM ProductModel WHERE id=:id")
    fun getProductById(id: Long): ProductModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(vararg productModel: ProductModel)

    @Delete
    fun deleteProduct(productModel: ProductModel)
}