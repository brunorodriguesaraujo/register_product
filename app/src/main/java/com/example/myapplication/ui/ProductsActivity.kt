package com.example.myapplication.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityProductsBinding
import com.example.myapplication.model.ProductModel

class ProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductsBinding
    private lateinit var viewModel: ProductViewModel
    private val adapter by lazy {  ProductAdapter(products) }
    private val products: MutableList<ProductModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observe()
        setListener()
        setAdapter()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
    }

    private fun setListener() {
        binding.productsFab.setOnClickListener {
            val intent = Intent(this, ProductRegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observe(){
        viewModel.product.observe(this) {
            products.add(it)
            adapter.notifyDataSetChanged()
        }
    }

    private fun setAdapter() {
        binding.rvProducts.adapter = adapter
    }
}