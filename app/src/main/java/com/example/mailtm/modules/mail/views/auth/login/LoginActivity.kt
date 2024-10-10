package com.example.mailtm.modules.mail.views.auth.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.mailtm.R
import com.example.mailtm.databinding.ActivityLoginBinding
import com.example.mailtm.databinding.ActivityMainBinding
import com.example.mailtm.modules.mail.views.auth.register.RegisterActivity

interface LoginActivityProps {
    fun showToast(message: String);
}

class LoginActivity : AppCompatActivity(), View.OnClickListener, LoginActivityProps {
    private lateinit var binding: ActivityLoginBinding;
    private val viewModel: LoginViewModel by viewModels {
        LoginActivityViewModelFactory(application, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater);
        setContentView(binding.root);

        bindings();
    }

    fun bindings() {
        binding.btnLogin.setOnClickListener(this);
        binding.linkRegister.setOnClickListener(this);
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.link_register -> {
                val intent = Intent(this, RegisterActivity::class.java);
                startActivity(intent);
            }

            R.id.btn_login -> {
                val address = binding.txtEditEmail.text.toString();
                val password = binding.txtEditPassword.text.toString();

                viewModel.login(address, password);
            }
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}