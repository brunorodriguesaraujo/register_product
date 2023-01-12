package com.example.myapplication.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.dao.ProductDAO
import com.example.myapplication.databinding.ActivityProductsBinding

class ProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListener()
    }

    override fun onResume() {
        super.onResume()
        setAdapter()
    }

    private fun setListener() {
        binding.productsFab.setOnClickListener {
            val intent = Intent(this, ProductRegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setAdapter() {
        val products = ProductDAO()
        binding.rvProducts.adapter = ProductAdapter(products.getProducts())
    }
}