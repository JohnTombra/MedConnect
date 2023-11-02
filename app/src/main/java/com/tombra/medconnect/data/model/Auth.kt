package com.tombra.medconnect.data.model

data class Auth(
    val authenticated: String,
    val authId: String,
    val accountType: String,
)
