package com.tombra.medconnect.data.model

data class ConnectAndMedicalPersonnel(
    val connect: Connect = Connect(),
    val medicalPersonnel: MedicalPersonnel = MedicalPersonnel()
)
