package com.example.myapplication.ui.product

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.constants.ID
import com.example.myapplication.constants.USER_ID
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.databinding.ActivityProductsBinding
import com.example.myapplication.preferences.dataStore
import kotlinx.coroutines.launch

class ProductsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductsBinding
    private val productDAO by lazy { AppDatabase.instance(this).productDao() }
    private val userDAO by lazy { AppDatabase.instance(this).userDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListener()
        setAdapter()
        getUserById()
    }

    private fun setListener() {
        binding.productsFab.setOnClickListener {
            startActivity(Intent(this, ProductRegisterActivity::class.java))
        }
    }

    private fun getUserById() {
        lifecycleScope.launch {
            dataStore.data.collect { preferences ->
                preferences[longPreferencesKey(USER_ID)]?.let { id ->
                    userDAO.getUserById(id)
                }
            }
        }
    }

    private fun setAdapter() {
        binding.rvProducts.apply {
            lifecycleScope.launch {
                productDAO.getProducts().collect {
                    adapter = ProductAdapter(it) {
                        Intent(this@ProductsActivity, ProductDetailActivity::class.java)
                            .apply {
                                putExtra(ID, it.id)
                                startActivity(this)
                            }
                    }
                }
            }
        }
    }
}