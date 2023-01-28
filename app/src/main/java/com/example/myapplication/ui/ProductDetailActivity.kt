package com.example.myapplication.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.constants.ID
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.databinding.ActivityProductDetailBinding
import com.example.myapplication.extension.loadUrl
import com.example.myapplication.extension.toFormatCurrencyBrazilian
import com.example.myapplication.model.ProductModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding
    private var product: ProductModel? = null
    private val productDao by lazy {
        AppDatabase.instance(this).productDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        getProduct()
        setFields()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_product_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.itemEdit -> {
                Intent(this, ProductRegisterActivity::class.java)
                    .apply {
                        product?.let {
                            putExtra(ID, it.id)
                            startActivity(this)
                        }
                    }
            }
            R.id.itemDelete -> {
                product?.let {
                    lifecycleScope.launch(Dispatchers.IO) {
                        productDao.deleteProduct(it)
                    }
                    finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getProduct() {
        val id = intent.getLongExtra(ID, 0)
        lifecycleScope.launch(Dispatchers.IO) {
            product = productDao.getProductById(id)
        }
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