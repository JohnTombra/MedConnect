package com.tombra.medconnect.data.model

data class MedicalRecord(
    val patientId: String = "",
    val howAreYouFeeling: String = "",
    val headAche: Boolean = false,
    val sourness: Boolean = false,
    val pain: Boolean = false,
    val highTemperature: Boolean = false,
    val vomiting: Boolean = false,
    val nausea: Boolean = false,
    val weight: Double = 0.0,
    val height: Double = 0.0,
    val bloodPressure: Double = 0.0,
    val bodyTemperature: Double = 0.0
)
