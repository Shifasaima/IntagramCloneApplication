package com.example.instagramcloneapplication.ui.composable

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.instagramcloneapplication.R
import com.example.instagramcloneapplication.ui.PostViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: PostViewModel = hiltViewModel()) {

    val loggedInUser by viewModel.loggedInUser.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = rememberImagePainter(R.drawable.instagram_logo),
            contentDescription = "Profile Image",
            modifier = Modifier
                .width(182.dp)
                .height(50.dp)
        )
        Spacer(Modifier.height(36.dp))
        Image(
            painter = rememberImagePainter(loggedInUser?.profileImageUrl),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )
        Text(
            text = loggedInUser?.username ?: "user1",
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Button(
            onClick = { navController.navigate("main_screen") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .height(44.dp)
                .background(color = Color(0xFF3797EF))
        ) { Text(text = "Log in") }
        Text(
            text = "Switch accounts",
            color = Color.Blue,
            modifier = Modifier
                .padding(vertical = 8.dp)
                .clickable { navController.navigate("authorization_screen") })
        Text(
            text = "Don't have an account? Sign up.",
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
                .clickable { /* Handle sign up */ })
    }

}
