package com.example.instagramcloneapplication.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter

//@Composable
//fun LikesScreen(navController: NavController) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        Text(
//            text = "Likes",
//            style = MaterialTheme.typography.h5,
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//        LazyColumn(
//            contentPadding = PaddingValues(8.dp),
//            content = {
//                items(20) { index ->
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(8.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Image(
//                            painter = rememberImagePainter("https://placekitten.com/100/100?image=$index"),
//                            contentDescription = null,
//                            modifier = Modifier
//                                .size(50.dp)
//                                .clip(CircleShape)
//                        )
//                        Spacer(modifier = Modifier.width(8.dp))
//                        Column {
//                            Text("User $index liked your photo")
//                            Text("3 hours ago", style = MaterialTheme.typography.body2)
//                        }
//                    }
//                }
//            }
//        )
//    }
//}

@Composable
fun LikesScreen(navController: NavController) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Following", "You")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Likes",
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TabRow(selectedTabIndex = selectedTabIndex) {
            tabTitles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (selectedTabIndex) {
            0 -> FollowingTab()
            1 -> YouTab()
        }
    }
}

@Composable
fun FollowingTab() {
    LazyColumn(
        contentPadding = PaddingValues(8.dp)
    ) {
        items(20) { index ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberImagePainter("https://picsum.photos/200/300?grayscale"),
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text("User $index liked your photo")
                    Text("3 hours ago", style = MaterialTheme.typography.body2)
                }
            }
        }
    }
}

@Composable
fun YouTab() {
    LazyColumn(
        contentPadding = PaddingValues(8.dp)
    ) {
        item {
            Text(
                text = "Follow Requests",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        items(1) {
            FollowRequestItem(
                username = "karennne",
                time = "1h",
                profileImageUrl = "https://picsum.photos/200/300?grayscale"
            )
        }

        item {
            Text(
                text = "New",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        items(1) {
            LikeItem(
                username = "kiero_d, zackjohn and 26 others",
                time = "3h",
                profileImageUrl = "https://picsum.photos/200/300?grayscale"
            )
        }

        item {
            Text(
                text = "This Week",
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        items(3) { index ->
            when (index) {
                0 -> CommentItem(
                    username = "craig_love",
                    comment = "mentioned you in a comment: @jacob_w exactly..",
                    time = "2d",
                    profileImageUrl = "https://picsum.photos/200/300?grayscale"
                )
                else -> FollowItem(
                    username = "martini_rond",
                    time = "3d",
                    profileImageUrl = "https://picsum.photos/200/300?grayscale"
                )
            }
        }
    }
}

@Composable
fun FollowRequestItem(username: String, time: String, profileImageUrl: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(profileImageUrl),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text("$username liked your photo")
            Text(time, style = MaterialTheme.typography.body2)
        }
    }
}

@Composable
fun LikeItem(username: String, time: String, profileImageUrl: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(profileImageUrl),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text("$username liked your photo")
            Text(time, style = MaterialTheme.typography.body2)
        }
    }
}

@Composable
fun CommentItem(username: String, comment: String, time: String, profileImageUrl: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(profileImageUrl),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text("$username $comment")
            Text(time, style = MaterialTheme.typography.body2)
        }
    }
}

@Composable
fun FollowItem(username: String, time: String, profileImageUrl: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(profileImageUrl),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text("$username started following you")
            Text(time, style = MaterialTheme.typography.body2)
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { /* Handle follow back */ },
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Text(text = "Follow")
        }
    }
}
