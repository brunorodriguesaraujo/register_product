package com.example.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityProductDetailBinding
import com.example.myapplication.extension.loadUrl
import com.example.myapplication.extension.toFormatCurrencyBrazilian
import com.example.myapplication.model.ProductModel

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding
    private var bundle: Bundle? = Bundle()
    private var product: ProductModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getProduct()
        setFields()
    }

    private fun getProduct(){
        bundle = intent.extras
        product = bundle?.getParcelable("product")
    }

    private fun setFields() = with(binding) {
        product?.let {
            ivProductDetail.loadUrl(it.url)
            tvValue.text = it.value.toFormatCurrencyBrazilian()
            tvName.text = it.name
            tvDescription.text = it.description
        }

    }
}