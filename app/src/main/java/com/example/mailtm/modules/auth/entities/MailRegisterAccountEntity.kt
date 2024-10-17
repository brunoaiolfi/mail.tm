package com.example.mailtm.modules.auth.entities

import com.google.gson.annotations.SerializedName

data class MailRegisterAccountEntity (
    @SerializedName("@context")
    var _context: String,

    @SerializedName("@id")
    var _id: String,

    @SerializedName("@type")
    var _type: String,

    @SerializedName("id")
    var id: String,

    @SerializedName("address")
    var address: String,

    @SerializedName("quota")
    var quota: Int,

    @SerializedName("used")
    var used: Int,

    @SerializedName("isDisabled")
    var isDisabled: Boolean,

    @SerializedName("isDeleted")
    var isDeleted: Boolean,

    @SerializedName("createdAt")
    var createdAt: String,

    @SerializedName("updatedAt")
    var updatedAt: String
)


