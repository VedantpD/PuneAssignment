package com.example.punetest.model


data class ClosedIssue(
    val title: String,
    val created_at: String,
    val closed_at: String,
    val user: UserModel
)

data class UserModel(
    val login: String,
    val avatar_url: String
)
