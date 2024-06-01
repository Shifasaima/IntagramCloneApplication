package com.example.instagramcloneapplication.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.instagramcloneapplication.ui.PostViewModel

@Composable
fun ProfileScreen(navController: NavController, viewModel: PostViewModel = hiltViewModel()) {
    val posts by viewModel.userPosts.collectAsState()
    val loggedInUser by viewModel.loggedInUser.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(modifier = Modifier.align(Alignment.CenterHorizontally), text = loggedInUser?.username ?: "random", style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Image(
                painter = rememberImagePainter(loggedInUser?.profileImageUrl),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ProfileStat(number = loggedInUser?.posts.toString(), label = "Posts")
                ProfileStat(number = loggedInUser?.followers.toString(), label = "Followers")
                ProfileStat(number = loggedInUser?.following.toString(), label = "Following")
            }
        }

        Column {
            Text(text = loggedInUser?.name ?: "random", style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
            Text(text = "Digital goodies designer @pixsellz", style = MaterialTheme.typography.body2)
            Text(text = "Everything is designed.", style = MaterialTheme.typography.body2)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StoryHighlight(name = "New")
            StoryHighlight(name = "Friends")
            StoryHighlight(name = "Sport")
            StoryHighlight(name = "Design")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Adaptive(120.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(posts) { post ->
                Image(
                    painter = rememberImagePainter(post.imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(8.dp)
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray)
                )
            }
        }
    }
}

@Composable
fun ProfileStat(number: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = number, style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
        Text(text = label, style = MaterialTheme.typography.body2)
    }
}

@Composable
fun StoryHighlight(name: String, img: String = "https://picsum.photos/200/300/?blur") {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = rememberImagePainter(img),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
                .border(border = BorderStroke(1.dp, Color.LightGray))
        )
//        Box(
//            modifier = Modifier
//                .size(64.dp)
//                .clip(CircleShape)
//                .background(Color.LightGray)
//        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = name, style = MaterialTheme.typography.body2)
    }
}
