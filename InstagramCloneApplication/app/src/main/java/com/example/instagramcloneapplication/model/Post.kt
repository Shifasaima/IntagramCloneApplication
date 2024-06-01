package com.example.instagramcloneapplication.model

data class Post(
    val id: Int,
    val username: String,
    val location: String,
    val imageUrl: String,
    val likes: Int,
    val description: String,
    val profileImageUrl: String
)
