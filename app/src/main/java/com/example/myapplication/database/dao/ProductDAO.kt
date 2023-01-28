package com.example.myapplication.database.dao

import androidx.room.*
import com.example.myapplication.model.ProductModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDAO {

    @Query("SELECT * FROM ProductModel")
    fun getProducts(): Flow<List<ProductModel>>

    @Query("SELECT * FROM ProductModel WHERE id=:id")
    fun getProductById(id: Long): Flow<ProductModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(vararg productModel: ProductModel)

    @Delete
    suspend fun deleteProduct(productModel: ProductModel)
}