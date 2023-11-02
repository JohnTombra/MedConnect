package com.tombra.medconnect.data.model

data class Prescription(
    val prescriptionId: String = "",
    val title: String = "",
    val dosage: String = "",
    val administration: String = "",
    val notes: String  = "",
)
