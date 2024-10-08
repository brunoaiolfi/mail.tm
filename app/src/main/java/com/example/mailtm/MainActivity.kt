package com.example.mailtm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.mailtm.databinding.ActivityMainBinding
import com.example.mailtm.modules.mail.views.RegisterActivity
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
//        binding.btnRegister.setOnClickListener(this);
        binding.linkRegister.setOnClickListener(this);
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.link_register -> {
                val intent = Intent(this, RegisterActivity::class.java);
                startActivity(intent);
            }
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}