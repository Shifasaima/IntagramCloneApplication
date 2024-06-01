package com.example.instagramcloneapplication.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.instagramcloneapplication.model.Post

//@Composable
//fun InstagramPost(post: Post) {
//    Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            Image(painter = rememberImagePainter(post.profileImageUrl), contentDescription = "Profile Image", modifier = Modifier.size(40.dp).clip(CircleShape), contentScale = ContentScale.Crop)
//            Spacer(modifier = Modifier.width(8.dp))
//            Column {
//                Text(text = post.username, fontWeight = FontWeight.Bold)
//                Text(text = post.location)
//            }
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Image(painter = rememberImagePainter(post.imageUrl), contentDescription = "Post Image", modifier = Modifier.fillMaxWidth(), contentScale = ContentScale.Crop)
//        Spacer(modifier = Modifier.height(8.dp))
//        Text(text = "Liked by ${post.likes}")
//        Spacer(modifier = Modifier.height(4.dp))
//        Text(text = post.description)
//    }
//}
