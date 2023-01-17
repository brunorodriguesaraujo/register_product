package com.example.myapplication.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.database.AppDatabase
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
            startActivity(Intent(this, ProductRegisterActivity::class.java))
        }
    }

    private fun setAdapter() {
        val db = AppDatabase.instance(this)
        val productDAO = db.productDao()
        binding.rvProducts.apply {
            adapter = ProductAdapter(productDAO.getProducts()) {
                val intent = Intent(this@ProductsActivity, ProductDetailActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelable("product", it)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }
}