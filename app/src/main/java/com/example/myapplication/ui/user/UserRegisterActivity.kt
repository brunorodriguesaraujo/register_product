package com.example.myapplication.ui.user

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.R
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.databinding.ActivityUserRegisterBinding
import com.example.myapplication.extension.setErrorEmptyField
import com.example.myapplication.model.UserModel
import com.example.myapplication.model.isValid
import com.example.myapplication.securance.hash
import kotlinx.coroutines.launch

class UserRegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserRegisterBinding
    private var userModel = UserModel()
    private val userDao by lazy { AppDatabase.instance(this).userDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buttonIsEnabled()
        setTitle()
        setFields()
        setListener()
    }

    private fun setTitle() {
        title = getString(R.string.register_user)
    }

    private fun buttonIsEnabled() {
        binding.btnSave.isEnabled = userModel.isValid()
    }

    private fun setFields() = with(binding) {
        textInputEditTextUsername.apply {
            doOnTextChanged { _, _, _, _ ->
                userModel = userModel.copy(username = this.text.toString())
                buttonIsEnabled()
            }
            doAfterTextChanged {
                textInputLayoutUsername.setErrorEmptyField(this.text.toString())
            }
        }
        textInputEditTextName.apply {
            doOnTextChanged { _, _, _, _ ->
                userModel = userModel.copy(name = this.text.toString())
                buttonIsEnabled()
            }
            doAfterTextChanged {
                textInputLayoutName.setErrorEmptyField(this.text.toString())
            }
        }
        textInputEditTextPassword.apply {
            doOnTextChanged { _, _, _, _ ->
                userModel = userModel.copy(password = this.text.toString().hash())
                buttonIsEnabled()
            }
            doAfterTextChanged {
                textInputLayoutPassword.setErrorEmptyField(this.text.toString())
            }
        }
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