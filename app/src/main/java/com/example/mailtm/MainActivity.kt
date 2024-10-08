package com.example.mailtm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.mailtm.databinding.ActivityMainBinding
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

interface MainActivityProps {
    fun showToast(message: String);
}

class MainActivity : AppCompatActivity(), View.OnClickListener, MainActivityProps {
    private lateinit var binding: ActivityMainBinding;
    private val viewModel: MainActivityViewModel by viewModels {
        MainActivityViewModelFactory(application, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        bindings();
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

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}