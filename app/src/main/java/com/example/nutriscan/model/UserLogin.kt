package com.example.nutriscan.model

import com.google.gson.annotations.SerializedName

data class UserLogin (
    @field:SerializedName("loginResult")
    val loginResult: LoginResult,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)