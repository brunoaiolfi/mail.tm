package com.example.mailtm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.mailtm.databinding.ActivityMainBinding
import com.example.mailtm.modules.mail.views.auth.login.LoginActivity
import com.example.mailtm.modules.mail.views.auth.register.RegisterActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root);

        val intent = Intent(this, LoginActivity::class.java);
        startActivity(intent);

        finish()
    }
}