package com.example.myapplication.extension

import android.app.Activity
import android.content.Intent

fun Activity.gotoStartActivity(clazz: Class<*>, intent: Intent.() -> Unit = {}) {
    Intent(this, clazz).apply {
        intent()
        startActivity(this)
    }
}