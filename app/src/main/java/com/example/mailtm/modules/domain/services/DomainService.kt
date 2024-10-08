package com.example.mailtm.modules.domain.services

import DomainEntity
import retrofit2.Call
import retrofit2.http.GET

interface  DomainService {
    @GET("domains")
    fun getDomains(): Call<DomainEntity>;
}