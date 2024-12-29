package com.example.feature.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.domain.model.RepositoryItem
import com.example.feature.AppScreens
import com.example.feature.screen.DetailScreen
import com.example.feature.screen.InputScreen
import com.example.feature.screen.RepositoryListScreen
import com.example.feature.utils.JsonUtils
import com.google.gson.Gson

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AppScreens.InputScreen.name,
        modifier = modifier
    ) {
        composable(route = AppScreens.InputScreen.name) {
            InputScreen(
                onSearch = { keyWord ->
                    val safeName = Uri.encode(keyWord)
                    navController.navigateSingleTopTo(
                        "${AppScreens.RepositoryListScreen.name}/$safeName"
                    )
                }
            )
        }
        composable(
            route = "${AppScreens.RepositoryListScreen.name}/{keyWord}",
            arguments = listOf(navArgument("keyWord") { type = NavType.StringType })
        ) { backStackEntry ->
            RepositoryListScreen(
                inputText = backStackEntry.arguments?.getString("keyWord") ?: "",
                onItemClick = { repositoryItem ->
                    val repositoryJson = Uri.encode(JsonUtils.toJson(repositoryItem))
                    navController.navigateSingleTopTo("${AppScreens.DetailScreen.name}/$repositoryJson")
                }
            )
        }
        composable(
            route = "${AppScreens.DetailScreen.name}/{repositoryJson}",
            arguments = listOf(navArgument("repositoryJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val repositoryJson = backStackEntry.arguments?.getString("repositoryJson")
            val repositoryItem = JsonUtils.fromJson(repositoryJson ?: "", RepositoryItem::class.java)

            DetailScreen(repositoryItem = repositoryItem)
        }
    }
}

private fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        launchSingleTop = true
        restoreState = true
    }
