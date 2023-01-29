package com.example.myapplication.ui.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.databinding.ActivityUserRegisterBinding
import com.example.myapplication.extension.setErrorEmptyField
import com.example.myapplication.model.UserModel
import com.example.myapplication.model.isValid
import kotlinx.coroutines.launch

class UserRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserRegisterBinding
    private var userModel = UserModel()
    private val userDao = AppDatabase.instance(this).userDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isEnabled()
        setTitle()
        setFields()
        setErrorFields()
        setListener()
    }

    private fun setTitle() {
        title = getString(R.string.register_user)
    }

    private fun isEnabled() {
        binding.btnSave.isEnabled = userModel.isValid()
    }

    private fun setFields() = with(binding) {
        textInputEditTextUsername.doOnTextChanged { _, _, _, _ ->
            userModel = userModel.copy(username = textInputEditTextUsername.text.toString())
            isEnabled()
        }
        textInputEditTextName.doOnTextChanged { _, _, _, _ ->
            userModel = userModel.copy(name = textInputEditTextName.text.toString())
            isEnabled()
        }
        textInputEditTextPassword.doOnTextChanged { _, _, _, _ ->
            userModel = userModel.copy(password = textInputEditTextPassword.text.toString())
            isEnabled()
        }
    }

    private fun setErrorFields() = with(binding) {
        textInputLayoutUsername.setErrorEmptyField(textInputEditTextUsername.text.toString())
        textInputLayoutName.setErrorEmptyField(textInputEditTextName.text.toString())
        textInputLayoutPassword.setErrorEmptyField(textInputEditTextPassword.text.toString())
    }

    private fun setListener() = with(binding) {
        btnSave.setOnClickListener {
            lifecycleScope.launch {
                userDao.registerUser(userModel)
                finish()
            }
        }
    }
}