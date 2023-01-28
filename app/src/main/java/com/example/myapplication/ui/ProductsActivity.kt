package com.example.myapplication.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.constants.ID
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.databinding.ActivityProductsBinding
import com.example.myapplication.model.ProductModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
            startActivity(Intent(this, ProductRegisterActivity::class.java))
        }
    }

    private fun setAdapter() {

        binding.rvProducts.apply {
            adapter = ProductAdapter(getProducts()) {
                val intent = Intent(this@ProductsActivity, ProductDetailActivity::class.java)
                intent.putExtra(ID, it.id)
                startActivity(intent)
            }
        }
    }

    private fun getProducts(): List<ProductModel> {
        val productDAO = AppDatabase.instance(this).productDao()
        var list: List<ProductModel> = emptyList()
        lifecycleScope.launch(Dispatchers.IO) {
            list = productDAO.getProducts()
        }
        return list
    }
}