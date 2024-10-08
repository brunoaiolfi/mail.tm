package com.example.mailtm.infra

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient

class RetrofitClient {
    companion object {
        private lateinit var retrofit: Retrofit;

        private fun getRetrofitInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder().build();

            if (!::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl("https://api.mail.tm/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build()
            }

            return retrofit;
        }

        fun <T> createService(service: Class<T>): T {
            return getRetrofitInstance().create(service)
        }
    }
}