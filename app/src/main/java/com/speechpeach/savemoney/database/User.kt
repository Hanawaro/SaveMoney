package com.speechpeach.savemoney.database

data class User(
    val uid: String = "",
    val email: String = "",
    val name: String = ""
) {
    companion object {
        const val collection = "users"
    }
}