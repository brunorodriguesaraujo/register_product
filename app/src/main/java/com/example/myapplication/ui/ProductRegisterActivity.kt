package com.example.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.database.dao.ProductDAO
import com.example.myapplication.databinding.ActivityProductRegisterBinding
import com.example.myapplication.databinding.LayoutAddImageBinding
import com.example.myapplication.extension.loadUrl
import com.example.myapplication.model.ProductModel

class ProductRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductRegisterBinding
    private var url = ""
    private var product: ProductModel? = null
    private var productDAO: ProductDAO? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDatabase()
        setListenerImage()
        setListenerButtonSave()
        getProduct()
        setFields()
    }

    private fun initDatabase() {
        productDAO = AppDatabase.instance(this).productDao()
    }

    private fun getProduct() {
        val bundle = intent.extras
        product = bundle?.getParcelable("product")
    }

    private fun setFields() = with(binding) {
        product?.let {
            textInputEditTextName.setText(it.name)
            textInputEditTextDescription.setText(it.description)
            textInputEditTextValue.setText(it.value.toString())
        }

    }

    private fun setListenerImage() {
        binding.ivProduct.setOnClickListener {
            val dialogBinding = LayoutAddImageBinding.inflate(layoutInflater)
            dialogBinding.apply {
                btnLoad.setOnClickListener {
                    url = textInputEditTextUrl.text.toString()
                    ivProductDialog.loadUrl(url)
                }
            }
            AlertDialog.Builder(this)
                .setView(dialogBinding.root)
                .setPositiveButton("Salvar") { _, _ ->
                    binding.ivProduct.loadUrl(url)
                }
                .setNegativeButton("Cancelar") { _, _ -> }
                .create()
                .show()
        }
    }

    private fun setListenerButtonSave() = with(binding) {
        btnSave.setOnClickListener {
            val productModel = ProductModel(
                url = this@ProductRegisterActivity.url,
                name = textInputEditTextName.text.toString(),
                description = textInputEditTextDescription.text.toString(),
                value = textInputEditTextValue.text.toString().toBigDecimal()
            )
            if (product != null) {
                productDAO?.updateProduct(productModel)
            } else {
                productDAO?.addProduct(productModel)
            }
            finish()
        }
    }
}