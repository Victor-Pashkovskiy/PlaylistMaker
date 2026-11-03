package com.example.lab2
import androidx.navigation.NavHostController
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


enum class Screen(val route: String) {
    MAIN("main"),
    SEARCH("search"),
    SETTINGS("settings")
}


@Composable
fun PlaylistHost(navController: NavHostController = rememberNavController()) {


    val navigateToSearch = { navController.navigate(Screen.SEARCH.route) }
    val navigateToSettings = { navController.navigate(Screen.SETTINGS.route) }
    val navigateBack = { navController.popBackStack() }

    NavHost(
        navController = navController,
        startDestination = Screen.MAIN.route
    ) {
        composable(Screen.MAIN.route) {
            MainScreen(
                onSearchClick = navigateToSearch,
                onSettingsClick = navigateToSettings
            )
        }
        composable(Screen.SEARCH.route) {
            SearchScreen()

        }
        composable(Screen.SETTINGS.route) {
            SettingsScreen(
                onBackClick = navigateBack
            )
        }
    }
}