package com.example.instagramcloneapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.instagramcloneapplication.ui.PostViewModel
import com.example.instagramcloneapplication.ui.composable.AuthorizationScreen
import com.example.instagramcloneapplication.ui.composable.LikesScreen
import com.example.instagramcloneapplication.ui.composable.LoginScreen
import com.example.instagramcloneapplication.ui.composable.ProfileScreen
import com.example.instagramcloneapplication.ui.composable.SearchScreen
import com.example.instagramcloneapplication.ui.theme.InstagramCloneApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.instagramcloneapplication.ui.composable.BottomNavigationBar
import com.example.instagramcloneapplication.ui.composable.MainScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InstagramCloneApplicationTheme {
                val navController = rememberNavController()
                HomeScreen(navController)
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    val currentDestination = currentDestination(navController)
    val showBottomBar = currentDestination?.route !in listOf("login_screen/{username}", "authorization_screen")

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            InstagramNavGraph(navController = navController, modifier = Modifier.weight(1f))
        }
        if (showBottomBar) {
            Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                BottomNavigationBar(navController = navController)
            }
        }
    }
}

@Composable
fun currentDestination(navController: NavHostController): NavDestination? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination
}

@Composable
fun InstagramNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    val viewModel: PostViewModel = hiltViewModel()
    val loggedInUser by viewModel.loggedInUser.collectAsState()

    val startDestination = if (loggedInUser?.username.isNullOrEmpty()) {
        "authorization_screen"
    } else {
        "login_screen"
    }

    NavHost(navController, startDestination = startDestination, modifier = modifier) {
        composable(route = "login_screen") {
            LoginScreen(navController = navController)
        }
        composable(route = "authorization_screen") {
            AuthorizationScreen(navController = navController)
        }
        composable(route = "main_screen") {
            MainScreen(navController = navController)
        }
        composable(route = "search_screen") {
            SearchScreen(navController = navController)
        }
        composable(route = "likes_screen") {
            LikesScreen(navController = navController)
        }
        composable(route = "profile_screen") {
            ProfileScreen(navController = navController)
        }
    }
}
