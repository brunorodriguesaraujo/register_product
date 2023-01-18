package com.example.myapplication.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.database.dao.ProductDAO
import com.example.myapplication.databinding.ActivityProductDetailBinding
import com.example.myapplication.extension.loadUrl
import com.example.myapplication.extension.toFormatCurrencyBrazilian
import com.example.myapplication.model.ProductModel

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductDetailBinding
    private var product: ProductModel? = null
    private var productDao: ProductDAO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        productDao = AppDatabase.instance(this).productDao()
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
                val intent = Intent(this, ProductRegisterActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelable("product", product)
                intent.putExtras(bundle)
                startActivity(intent)
                finish()
            }
            R.id.itemDelete -> {
                product?.let {
                    productDao?.deleteProduct(it)
                    finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getProduct() {
        val id = intent.getLongExtra("id", 0)
        product = productDao?.getProductById(id)
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