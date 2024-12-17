@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.catapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.catapp.presentation.screens.BottomNavigationBar
import com.example.catapp.presentation.screens.MainTabScreen
import com.example.catapp.presentation.screens.NotificationScreen
import com.example.catapp.presentation.screens.PictureDetailsScreen
import com.example.catapp.presentation.screens.PictureListScreen
import com.example.catapp.presentation.screens.navigationItems
import androidx.navigation.NavController
import com.example.catapp.ui.theme.CatappTheme
import com.github.terrakok.modo.Modo.rememberRootScreen
import com.github.terrakok.modo.RootScreen
import com.github.terrakok.modo.stack.DefaultStackScreen
import com.github.terrakok.modo.stack.StackNavModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val controller = rememberNavController()
    var currentRoute by remember { mutableStateOf("home") }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    if (currentRoute == "list") {
                        Text("Картины")
                    } else {
                        Text("")
                    }
                },
                navigationIcon = {
                    if (currentRoute.startsWith("element/")) {
                        IconButton(onClick = { controller.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Назад"
                            )
                        }
                    }
                }
            )
        },

        bottomBar = { BottomNavigationBar(controller, currentRoute) { currentRoute = it } }
    ) { innerPadding ->
        NavHost(navController = controller, startDestination = navigationItems.first().route) {
            composable("home") {
                currentRoute = "home"
                MainTabScreen()
            }
            composable("notification") {
                currentRoute = "notification"
                NotificationScreen()
            }
            composable("list") {
                currentRoute = "list"
                PictureListScreen(controller)
            }
            composable("element/{elementId}") { backStackEntry ->
                val elementId = backStackEntry.arguments?.getString("elementId")?.toInt() ?: 0
                currentRoute = "element/$elementId"
                PictureDetailsScreen(controller, elementId)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainScreen()
}
