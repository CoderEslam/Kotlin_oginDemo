package net.simplifiedcoding.loginapp.data.network.responses

import net.simplifiedcoding.loginapp.data.db.entities.User

data class AuthResponse(
    val errorCode : String?,
    val errorMessage: String?,
    val user: User?
)