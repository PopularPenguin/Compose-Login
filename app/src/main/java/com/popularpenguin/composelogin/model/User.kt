package com.popularpenguin.composelogin.model

data class User(
    val email: String, // primary key, must be unique
    val name: String = "",
    val password: String = "",
    val authToken: String? = null
)