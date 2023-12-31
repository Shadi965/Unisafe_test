package ru.unisafe.retrofit.auth.entities

import com.squareup.moshi.Json

data class AuthResponse(
    @Json(name = "success")
    val status: Boolean,
    val error: String?
)
