package com.example.mailtm.modules.domain.repository

import DomainEntity
import com.example.mailtm.infra.RetrofitClient
import com.example.mailtm.modules.domain.services.DomainService
import retrofit2.Call

class DomainRepository {
    companion object {
        private val remote = RetrofitClient.createService(DomainService::class.java)

        fun getDomains(
            cb: (DomainEntity) -> Unit,
            cbError: (message: String) -> Unit
        ) {
            val call: Call<DomainEntity> = remote.getDomains();

            call.enqueue(object : retrofit2.Callback<DomainEntity> {
                override fun onResponse(
                    call: Call<DomainEntity>,
                    response: retrofit2.Response<DomainEntity>
                ) {
                    if (!response.isSuccessful) return cbError(response.message());

                    val domain = response.body();
                    return cb(domain!!);
                }

                override fun onFailure(call: Call<DomainEntity>, t: Throwable) {
                    cbError(t.message.toString());
                }
            })
        }
    }
}