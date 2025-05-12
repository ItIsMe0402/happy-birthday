package com.github.itisme0402.happybirthday

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.github.itisme0402.happybirthday.domain.BirthdayInfo
import com.github.itisme0402.happybirthday.navigation.Destination
import com.github.itisme0402.happybirthday.navigation.JsonSerializableNavType
import com.github.itisme0402.happybirthday.settings.SettingsScreen
import com.github.itisme0402.happybirthday.theming.HappyBirthdayTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.reflect.typeOf

@Composable
@Preview
fun App() {
    HappyBirthdayTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Destination.Settings,
        ) {
            composable<Destination.Settings> {
                SettingsScreen(
                    onNavigateToGreeting = { birthdayInfo ->
                        navController.navigate(Destination.Greeting(birthdayInfo))
                    },
                )
            }
            composable<Destination.Greeting>(
                typeMap = mapOf(
                    typeOf<BirthdayInfo>() to JsonSerializableNavType.create<BirthdayInfo>(),
                ),
            ) { navBackStackEntry ->
                val birthdayInfo = navBackStackEntry.toRoute<Destination.Greeting>().birthdayInfo
                HappyBirthdayScreen(
                    onGoBack = navController::popBackStack,
                    birthdayInfo = birthdayInfo,
                )
            }
        }
    }
}
