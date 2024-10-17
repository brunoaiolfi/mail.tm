package com.example.mailtm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mailtm.databinding.ActivityMainBinding
import com.example.mailtm.modules.auth.views.auth.login.LoginActivity

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