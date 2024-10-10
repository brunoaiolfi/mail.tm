package com.example.mailtm.modules.mail.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.mailtm.R
import com.example.mailtm.databinding.ActivityRegisterBinding

interface RegisterActivityProps {
    fun showToast(message: String);
    fun returnToLogin();
}

class RegisterActivity : AppCompatActivity(), View.OnClickListener, RegisterActivityProps {
    private lateinit var binding: ActivityRegisterBinding;
    private val viewModel: RegisterActivityViewModel by viewModels {
        RegisterActivityViewModelFactory(application, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater);
        setContentView(binding.root);

        bindings();
        observers();
    }

    fun bindings() {
        binding.btnRegister.setOnClickListener(this);
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_register -> {
                viewModel.register(
                    binding.txtEditUsername.text.toString(),
                    binding.txtEditPassword.text.toString(),
                    binding.txtEditPasswordConfirmation.text.toString()
                );
            }
        }
    }

    fun observers() {
        viewModel.isLoading.observe(this, {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.INVISIBLE;
        })
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    override fun returnToLogin() {
        finish();
    }
}