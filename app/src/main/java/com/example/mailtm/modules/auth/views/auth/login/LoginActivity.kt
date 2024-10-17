package com.example.mailtm.modules.auth.views.auth.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.mailtm.R
import com.example.mailtm.databinding.ActivityLoginBinding
import com.example.mailtm.modules.auth.entities.AuthenticationEntity
import com.example.mailtm.modules.auth.views.auth.register.RegisterActivity

interface LoginActivityProps {
    fun showToast(message: String);
    fun loginCallback(authenticationEntity: AuthenticationEntity);
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
        observer();
    }

    fun bindings() {
        binding.btnLogin.setOnClickListener(this);
        binding.linkRegister.setOnClickListener(this);
    }

    fun observer() {
        viewModel.isLoading.observe(this, {
            if (it) {
                binding.btnLogin.isEnabled = false;
                binding.btnLogin.text = "Loading...";
            } else {
                binding.btnLogin.isEnabled = true;
                binding.btnLogin.text = "Login";
            }
        });
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

    override fun loginCallback(authenticationEntity: AuthenticationEntity) {
        saveToken(authenticationEntity.token);
        saveUserId(authenticationEntity.id);
    }

    private fun saveToken(token: String) {
        val sharedPref = getSharedPreferences("mailtm", MODE_PRIVATE);
        val editor = sharedPref.edit();

        editor.putString("token", token);
        editor.apply();
    }

    private fun saveUserId(userId: String) {
        val sharedPref = getSharedPreferences("mailtm", MODE_PRIVATE);
        val editor = sharedPref.edit();

        editor.putString("userId", userId);
        editor.apply();
    }
}