package com.tombra.medconnect.data.model

data class ConnectAndPatient(
    val connect: Connect = Connect(),
    val patient: Patient = Patient()
)
