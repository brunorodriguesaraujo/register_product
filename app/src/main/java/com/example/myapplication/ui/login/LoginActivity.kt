package com.example.myapplication.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.extension.setErrorEmptyField
import com.example.myapplication.model.LoginModel
import com.example.myapplication.model.isValid
import com.example.myapplication.ui.product.ProductsActivity
import com.example.myapplication.ui.user.UserRegisterActivity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var loginModel = LoginModel()
    private val userDao by lazy {
        AppDatabase.instance(this).userDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buttonIsEnabled()
        setFields()
        setListener()
    }

    private fun buttonIsEnabled() {
        binding.btnEnter.isEnabled = loginModel.isValid()
    }

    private fun setFields() = with(binding) {
        textInputEditTextUsername.apply {
            doOnTextChanged { _, _, _, _ ->
                loginModel = loginModel.copy(username = this.text.toString())
                buttonIsEnabled()
            }
            doAfterTextChanged {
                textInputLayoutUsername.setErrorEmptyField(this.text.toString())
            }
        }
        textInputEditTextPassword.apply {
            doOnTextChanged { _, _, _, _ ->
                loginModel = loginModel.copy(password = this.text.toString())
                buttonIsEnabled()
            }
            doAfterTextChanged {
                textInputLayoutPassword.setErrorEmptyField(this.text.toString())
            }
        }
    }

    private fun setListener() = with(binding) {
        btnRegisterUser.setOnClickListener {
            startActivity(
                Intent(
                    this@LoginActivity,
                    UserRegisterActivity::class.java
                )
            )
        }
        btnEnter.setOnClickListener {
            lifecycleScope.launch {
                userDao.authUser(
                    textInputEditTextUsername.text.toString(),
                    textInputEditTextPassword.text.toString().hash()
                )?.let {
                    startActivity(
                        Intent(
                            this@LoginActivity,
                            ProductsActivity::class.java
                        )
                    )
                    finish()
                } ?: Toast.makeText(
                    this@LoginActivity,
                    "Usuário não está cadastrado",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}