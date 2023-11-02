package com.tombra.medconnect.data.model

data class Request(
    val requestId: String = "",
    val patientId: String = "",
    val description: String = "",
    val department: String = "",
    val subDepartment: String = ""
)
