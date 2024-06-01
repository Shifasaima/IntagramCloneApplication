package com.example.instagramcloneapplication.internal

import com.example.instagramcloneapplication.model.Post
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostRepository @Inject constructor() {

    private val mockUsers = listOf(
        User("user1", "password1"),
        User("user2", "password2")
    )

    private var loggedInUser: User? = mockUsers[0]

    private val mockPosts = listOf(
        Post(1, "joshua_l", "Tokyo, Japan", "https://picsum.photos/200/300", 44686, "The game in Japan was amazing and I want to share some photos", "https://picsum.photos/200/300"),
        Post(2, "karennne", "New York, USA", "https://picsum.photos/200/300", 13245, "Enjoying the sunny day!", "https://picsum.photos/200/300"),
        Post(3, "user1", "New York, USA", "https://picsum.photos/200/300", 13245, "Enjoying the sunny day!", "https://picsum.photos/200/300"),
        Post(4, "user1", "New York, USA", "https://picsum.photos/200/300", 13245, "Enjoying the sunny day!", "https://picsum.photos/200/300"),
        Post(5, "user1", "New York, USA", "https://picsum.photos/200/300", 13245, "Enjoying the sunny day!", "https://picsum.photos/200/300"),
        Post(6, "user1", "New York, USA", "https://picsum.photos/200/300", 13245, "Enjoying the sunny day!", "https://picsum.photos/200/300"),
        Post(7, "user1", "New York, USA", "https://picsum.photos/200/300", 13245, "Enjoying the sunny day!", "https://picsum.photos/200/300"),
        Post(8, "user1", "New York, USA", "https://picsum.photos/200/300", 13245, "Enjoying the sunny day!", "https://picsum.photos/200/300")
    )

    fun getAllPosts(): List<Post> {
        return mockPosts
    }

    fun getUserPosts(username: String): List<Post> {
        return mockPosts.filter { it.username == username }
    }

    fun authenticateUser(username: String, password: String): User? {
        val userExists = mockUsers.any { it.username == username && it.password == password }
        return if (userExists) {
            loggedInUser = mockUsers.find { it.username == username }
            loggedInUser
        } else {
            null
        }
    }

    fun getLoggedInUser(): User? {
        return loggedInUser
    }

    data class User(val username: String, val password: String, val name: String = "User's Name", val profileImageUrl: String = "https://picsum.photos/200/300", val posts: Int = 78, val followers: Int = 666, val following: Int = 422)
}
