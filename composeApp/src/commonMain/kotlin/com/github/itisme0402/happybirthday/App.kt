package com.github.itisme0402.happybirthday

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Destination.Settings,
        ) {
            composable<Destination.Settings> {
                SettingsScreen(
                    onNavigateToGreeting = { navController.navigate(Destination.Greeting) },
                )
            }
            composable<Destination.Greeting> {
                HappyBirthdayScreen()
            }
        }
    }
}
