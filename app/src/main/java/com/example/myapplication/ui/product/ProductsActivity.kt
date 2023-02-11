package com.example.myapplication.ui.product

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.constants.USER_ID
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.databinding.ActivityProductsBinding
import com.example.myapplication.extension.gotoStartActivity
import com.example.myapplication.preferences.dataStore
import com.example.myapplication.ui.login.LoginActivity
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_products, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemLogout -> {
                lifecycleScope.launch {
                    dataStore.edit { preferences ->
                        preferences.remove(longPreferencesKey(USER_ID))
                    }
                }
                gotoStartActivity(LoginActivity::class.java)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setListener() {
        binding.productsFab.setOnClickListener {
            gotoStartActivity(ProductRegisterActivity::class.java)
        }
    }

    private fun getUserById() {
        lifecycleScope.launch {
            dataStore.data.collect { preferences ->
                preferences[longPreferencesKey(USER_ID)]?.let { id ->
                    launch { userDAO.getUserById(id) }
                } ?: gotoStartActivity(LoginActivity::class.java)
            }
        }
    }

    private fun setAdapter() {
        binding.rvProducts.apply {
            lifecycleScope.launch {
                productDAO.getProducts().collect {
                    adapter = ProductAdapter(it) {
                        gotoStartActivity(ProductDetailActivity::class.java)
                    }
                }
            }
        }
    }
}