package com.example.mailtm.modules.mail.services

import com.example.mailtm.modules.mail.entities.MailAccountEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MailAuthenticationServices {

    data class MailCreateAccountDTO(
        val address: String,
        val password: String,
    )

    @POST("accounts")
    fun createAccount(
        @Body() body: MailCreateAccountDTO
    ): Call<MailAccountEntity>
}