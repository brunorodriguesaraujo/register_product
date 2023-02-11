package com.example.myapplication.ui.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemProductBinding
import com.example.myapplication.extension.loadUrl
import com.example.myapplication.extension.toFormatCurrencyBrazilian
import com.example.myapplication.model.ProductModel

class ProductAdapter(
    private val products: List<ProductModel>,
    private val listener: (product: ProductModel) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int = products.size

    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductModel) = with(binding) {
            ivProduct.loadUrl(product.url)
            tvTitle.text = product.name
            tvDescription.text = product.description
            tvValue.text = product.value.toFormatCurrencyBrazilian()
            root.setOnClickListener { listener(product) }
        }
    }
}