package com.example.mailtm.modules.mail.entities

import com.google.gson.annotations.SerializedName

data class AuthenticationEntity (
    @SerializedName("token")
    var token: String,

    @SerializedName("id")
    var id: String,
)
