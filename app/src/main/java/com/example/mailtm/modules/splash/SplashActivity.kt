package com.example.mailtm.modules.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mailtm.R
import com.example.mailtm.modules.auth.views.auth.login.LoginActivity
import com.example.mailtm.modules.mail.views.inbox.DashboardActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        verifyAuthentication();
    }

    private fun verifyAuthentication() {
        val sharedPreferences = getSharedPreferences("mailtm", MODE_PRIVATE)
        val token = sharedPreferences.getString("token", null)
        val destinationActivity =
            if (token.isNullOrEmpty()) LoginActivity::class.java else DashboardActivity::class.java;

        val intent = Intent(this, destinationActivity);
        startActivity(intent);

        finish();
    }
}