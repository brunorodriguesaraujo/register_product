package com.example.myapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.model.ProductModel

class ProductViewModel: ViewModel() {

    private val _product: MutableLiveData<ProductModel> = MutableLiveData()
    val product: LiveData<ProductModel> = _product

    fun addProduct(product: ProductModel) {
        _product.value = product
    }
}