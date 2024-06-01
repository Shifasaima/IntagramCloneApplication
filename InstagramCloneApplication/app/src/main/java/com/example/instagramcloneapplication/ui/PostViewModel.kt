package com.example.instagramcloneapplication.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagramcloneapplication.internal.PostRepository
import com.example.instagramcloneapplication.internal.PostRepository.User
import com.example.instagramcloneapplication.model.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {
    private val _allPosts = MutableStateFlow<List<Post>>(emptyList())
    val allPosts: StateFlow<List<Post>> get() = _allPosts

    private val _userPosts = MutableStateFlow<List<Post>>(emptyList())
    val userPosts: StateFlow<List<Post>> get() = _userPosts

    val loginError = mutableStateOf("")
    private val _loggedInUser = MutableStateFlow<User?>(null)
    val loggedInUser: StateFlow<User?> get() = _loggedInUser

    init {
        viewModelScope.launch {
            _allPosts.value = repository.getAllPosts()
            _loggedInUser.value = repository.getLoggedInUser()
            _userPosts.value = repository.getUserPosts(_loggedInUser.value?.username ?: "")
        }
    }

    fun login(username: String, password: String, onResult: (Boolean) -> Unit) {
        val userData = repository.authenticateUser(username, password)
        if (userData != null) {
            loginError.value = ""
            _loggedInUser.value = userData
            onResult(true)
            viewModelScope.launch {
                _userPosts.value = repository.getUserPosts(username)
            }
        } else {
            loginError.value = "Invalid username or password"
            onResult(false)
        }
    }

}
