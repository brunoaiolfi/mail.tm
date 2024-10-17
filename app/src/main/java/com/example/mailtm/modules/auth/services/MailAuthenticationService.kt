package com.example.mailtm.modules.auth.services

import com.example.mailtm.modules.auth.entities.AuthenticationEntity
import com.example.mailtm.modules.auth.entities.MailRegisterAccountEntity
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