package com.example.instagramcloneapplication.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.instagramcloneapplication.R
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.instagramcloneapplication.ui.PostViewModel

@Composable
fun AuthorizationScreen(navController: NavController, viewModel: PostViewModel = hiltViewModel()) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val loginError by viewModel.loginError

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = rememberImagePainter(R.drawable.instagram_logo),
            contentDescription = "Instagram Logo",
            modifier = Modifier
                .width(182.dp)
                .height(50.dp)
        )
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )
        Button(
            onClick = {
                viewModel.login(username, password) { success ->
                    if (success) {
                        navController.navigate("main_screen")
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Log in")
        }
        if (loginError.isNotEmpty()) {
            Text(text = loginError, color = Color.Red, modifier = Modifier.padding(vertical = 8.dp))
        }
        Text(
            text = "Forgot password?",
            color = Color.Blue,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .clickable { /* Handle forgot password */ }
        )
        Button(
            onClick = { /* Handle login with Facebook */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text(text = "Log in with Facebook")
        }
        Text(
            text = "OR",
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Text(
            text = "Don't have an account? Sign up.",
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
                .clickable { /* Handle sign up */ }
        )
    }
}
