package com.example.instagramcloneapplication.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
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
import com.example.instagramcloneapplication.model.Post
import com.example.instagramcloneapplication.ui.PostViewModel

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: PostViewModel = hiltViewModel()
) {
    val posts by viewModel.allPosts.collectAsState()

    Column {
        TopAppBar(
            title = { Text(text = "Instagram") },
            actions = {
                IconButton(onClick = { /* Handle notifications */ }) {
                    Icon(
                        Icons.Default.Notifications,
                        contentDescription = "Notifications"
                    )
                }
                IconButton(onClick = { navController.navigate("profile_screen") }) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "Profile"
                    )
                }
            }
        )
        LazyColumn {
            item {
                StorySection()
            }
            items(posts) { post ->
                InstagramPost(post)
            }
        }
    }
}

@Composable
fun StorySection() {
    val stories = listOf(
        "Your Story", "karenne", "zackjohn", "kieron_d", "craig_love", "user2", "locus", "meghna"
    )

    LazyRow(
        modifier = Modifier.padding(8.dp),
        contentPadding = PaddingValues(horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(stories) { story ->
            StoryItem(story)
        }
    }
}

@Composable
fun StoryItem(story: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberImagePainter("https://picsum.photos/id/870/200/300?grayscale&blur=2"),
            contentDescription = "Story Image",
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
        )
        Text(text = story, style = MaterialTheme.typography.caption, fontWeight = FontWeight.Normal, modifier = Modifier.padding(top = 4.dp))
    }
}

@Composable
fun InstagramPost(post: Post) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberImagePainter(post.profileImageUrl),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(text = post.username, fontWeight = FontWeight.Bold)
                Text(text = post.location, color = Color.Gray)
            }
        }
        Image(
            painter = rememberImagePainter(post.imageUrl),
            contentDescription = "Post Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(vertical = 8.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /* Like post */ }) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Like")
            }
            Text(text = "${post.likes} likes", fontWeight = FontWeight.Bold)
        }
        Text(text = "${post.username} ${post.description}")
    }
}
