package com.example.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityProductRegisterBinding
import com.example.myapplication.databinding.LayoutAddImageBinding
import com.example.myapplication.extension.loadUrl

class ProductRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListenerImage()
    }

    private fun setListenerImage() {
        var url = ""
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
}