package com.tombra.medconnect.data.model


data class MedicalPersonnel(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val gender: String = "",
    val picture: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val department: String = "",
    val subDepartment: String = "",
    val verified: String = "",
    val locale: String = "",
    val state: String = "",
    val country: String = "",
    val preCharge: Double = 0.0,
    val active: Boolean = false,
    val comments: List<Comment> = listOf(),
    val rating: List<Rating> = listOf(),
    val location: MyLocation = MyLocation()
)
