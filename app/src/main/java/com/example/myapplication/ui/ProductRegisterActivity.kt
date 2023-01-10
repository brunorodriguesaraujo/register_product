package com.example.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityProductRegisterBinding
import com.example.myapplication.databinding.LayoutAddImageBinding
import com.example.myapplication.extension.loadUrl
import com.example.myapplication.model.ProductModel

class ProductRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductRegisterBinding
    private lateinit var viewModel: ProductViewModel
    private var url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        binding = ActivityProductRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListenerImage()
        setListenerButtonSave()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[ProductViewModel::class.java]
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

    private fun setListenerButtonSave() =  with(binding){
        btnSave.setOnClickListener {
            viewModel.addProduct(
                ProductModel(
                    url = this@ProductRegisterActivity.url,
                    name = textInputEditTextName.text.toString(),
                    description = textInputEditTextDescription.text.toString(),
                    value = textInputEditTextValue.text.toString().toBigDecimal()
                )
            )
            finish()
        }
    }
}