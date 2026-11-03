package com.example.lab2

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.lab2.domain.model.Track
import com.example.lab2.view_model.SearchViewModel
import com.google.gson.Gson

@Composable
fun PlaylistHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    gson: Gson,
    searchViewModel: SearchViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") {
            MainScreen(
                onSearchClick = { navController.navigate("search") },
                onSettingsClick = { navController.navigate("settings") }
            )
        }
        composable("search") {
            SearchScreen(
                searchViewModel = searchViewModel,
                navigateToDetailScreen = { track ->
                    val trackJson = gson.toJson(track)
                    navController.navigate("details/$trackJson")
                }
            )
        }
        composable("details/{trackJson}") { backStackEntry ->
            val trackJson = backStackEntry.arguments?.getString("trackJson")
            if (trackJson != null) {
                val track = gson.fromJson(trackJson, Track::class.java)
                DetailsScreen(track = track)
            }
        }
        composable("settings") {
            SettingsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}