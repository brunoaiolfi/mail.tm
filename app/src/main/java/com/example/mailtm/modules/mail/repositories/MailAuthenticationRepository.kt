package com.example.mailtm.modules.mail.repositories

import com.example.mailtm.infra.RetrofitClient
import com.example.mailtm.modules.mail.entities.MailRegisterAccountEntity
import com.example.mailtm.modules.mail.services.MailAuthenticationService
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
            cb: (Any) -> Unit,
            cbError: (message: String) -> Unit
        ) {
            val call: Call<Any> = remote.login(dto);

            call.enqueue(object : retrofit2.Callback<Any> {
                override fun onResponse(
                    call: Call<Any>,
                    response: retrofit2.Response<Any>
                ) {
                    if (!response.isSuccessful) return cbError(response.message());

                    val token = response.body();

                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    cbError(t.message.toString());
                }
            })
        }
    }
}