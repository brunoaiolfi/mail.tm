package com.example.mailtm.modules.mail.services

import com.example.mailtm.modules.mail.entities.AuthenticationEntity
import com.example.mailtm.modules.mail.entities.MailRegisterAccountEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MailAuthenticationService {

    data class MailRegisterAccountDTO(
        val address: String,
        val password: String
    )

    @POST("accounts")
    fun registerAccount(@Body() request: MailRegisterAccountDTO): Call<MailRegisterAccountEntity>

    @POST("/token")
    fun login(@Body() request: MailRegisterAccountDTO): Call<AuthenticationEntity>
}