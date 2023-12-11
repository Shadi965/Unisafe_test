package ru.unisafe.data.auth.entities

import com.squareup.moshi.Json

data class AuthStatus(
    @Json(name = "success")
    val status: Boolean,
    val error: String?
)
