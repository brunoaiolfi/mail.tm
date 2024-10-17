package com.example.mailtm.modules.auth.repositories

import com.example.mailtm.infra.RetrofitClient
import com.example.mailtm.modules.auth.entities.AuthenticationEntity
import com.example.mailtm.modules.auth.entities.MailRegisterAccountEntity
import com.example.mailtm.modules.auth.services.MailAuthenticationService
import retrofit2.Call

class MailAuthenticationRepository {
    companion object {
        private val remote = RetrofitClient.createService(MailAuthenticationService::class.java)

        fun register(
            dto: MailAuthenticationService.MailRegisterAccountDTO,
            cb: (MailRegisterAccountEntity) -> Unit,
            cbError: (message: String) -> Unit
        ) {
            val call: Call<MailRegisterAccountEntity> = remote.registerAccount(
                dto
            );

            call.enqueue(object : retrofit2.Callback<MailRegisterAccountEntity> {
                override fun onResponse(
                    call: Call<MailRegisterAccountEntity>,
                    response: retrofit2.Response<MailRegisterAccountEntity>
                ) {
                    print(dto)
                    if (!response.isSuccessful) return cbError(response.message());

                    val mailAccountEntity = response.body();
                    return cb(mailAccountEntity!!);
                }

                override fun onFailure(call: Call<MailRegisterAccountEntity>, t: Throwable) {
                    cbError(t.message.toString());
                }
            })
        }

        fun login(
            dto: MailAuthenticationService.MailRegisterAccountDTO,
            cb: (AuthenticationEntity) -> Unit,
            cbError: (message: String) -> Unit
        ) {
            val call: Call<AuthenticationEntity> = remote.login(dto);

            call.enqueue(object : retrofit2.Callback<AuthenticationEntity> {
                override fun onResponse(
                    call: Call<AuthenticationEntity>,
                    response: retrofit2.Response<AuthenticationEntity>
                ) {
                    if (!response.isSuccessful) return cbError(response.message());

                    val token = response.body();

                    cb(token!!);
                }

                override fun onFailure(call: Call<AuthenticationEntity>, t: Throwable) {
                    cbError(t.message.toString());
                }
            })
        }
    }
}