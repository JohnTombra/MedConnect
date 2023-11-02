package com.tombra.medconnect.data.model

data class Payment(
    val paymentId: String = "",
    val description: String = "",
    val paid: Boolean = false,
    val amount: Double = 0.0,
    val patientId: String = "",
    val medicalPersonnelId: String = "",
)
