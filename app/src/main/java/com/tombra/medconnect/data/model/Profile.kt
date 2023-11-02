package com.tombra.medconnect.data.model

data class Profile(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val gender: String = "",
    val phoneNumber: String = "",
    val email: String = "",
    val picture: String = "",
    val comments: List<Comment> = listOf(),
    val rating: List<Rating> = listOf(),
    val location: MyLocation = MyLocation()
)
