package com.example.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.constants.ID
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.databinding.ActivityProductRegisterBinding
import com.example.myapplication.databinding.LayoutAddImageBinding
import com.example.myapplication.extension.loadUrl
import com.example.myapplication.model.ProductModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductRegisterBinding
    private var url = ""
    private var product: ProductModel? = null
    private val productDao by lazy {
        AppDatabase.instance(this).productDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListenerImage()
        setListenerButtonSave()
        getProduct()
        setFields()
    }

    private fun getProduct() {
        val id = intent.getLongExtra(ID, 0L)
        lifecycleScope.launch(Dispatchers.IO) {
            product = productDao.getProductById(id)
        }
    }

    private fun setFields() = with(binding) {
        product?.let {
            ivProduct.loadUrl(it.url)
            textInputEditTextName.setText(it.name)
            textInputEditTextDescription.setText(it.description)
            textInputEditTextValue.setText(it.value.toPlainString())
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
                id = product?.id ?: 0,
                url = this@ProductRegisterActivity.url,
                name = textInputEditTextName.text.toString(),
                description = textInputEditTextDescription.text.toString(),
                value = textInputEditTextValue.text.toString().toBigDecimal()
            )
            lifecycleScope.launch(Dispatchers.IO) {
                productDao.addProduct(productModel)
            }
            finish()
        }
    }
}