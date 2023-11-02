package com.tombra.medconnect.data.model

data class Message(
    val timeStamp: String = "",
    val message: String = "",
    val sent: Boolean = false,
    val delivered: Boolean = false,
    val read: Boolean = false,
)
