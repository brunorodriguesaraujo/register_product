package com.example.myapplication.extension

import android.widget.ImageView
import coil.load
import com.example.myapplication.R

fun ImageView.loadUrl(url: String){
    load(url) {
        fallback(R.drawable.img_placeholder)
        error(R.drawable.error)
        placeholder(R.drawable.placeholder)
    }
}