package com.example.myapplication.dao

import com.example.myapplication.model.ProductModel

class ProductDAO {

    fun addProduct(product: ProductModel) {
        products.add(product)
    }

    fun getProducts(): List<ProductModel> {
        return products
    }

    companion object {
        val products: MutableList<ProductModel> = mutableListOf()
    }
}