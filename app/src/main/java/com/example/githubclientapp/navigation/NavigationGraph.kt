package com.example.githubclientapp.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.githubclientapp.GitHubAppScreens
import com.example.githubclientapp.model.RepositoryItem
import com.example.githubclientapp.ui.screen.DetailScreen
import com.example.githubclientapp.ui.screen.InputScreen
import com.example.githubclientapp.ui.screen.RepositoryListScreen
import com.google.gson.Gson

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = GitHubAppScreens.InputScreen.name,
        modifier = modifier
    ) {
        composable(route = GitHubAppScreens.InputScreen.name) {
            InputScreen(
                onSearch = { keyWord ->
                    val safeName = Uri.encode(keyWord)
                    navController.navigateSingleTopTo(
                        "${GitHubAppScreens.RepositoryListScreen.name}/$safeName"
                    )
                }
            )
        }
        composable(
            route = "${GitHubAppScreens.RepositoryListScreen.name}/{keyWord}",
            arguments = listOf(navArgument("keyWord") { type = NavType.StringType })
        ) { backStackEntry ->
            RepositoryListScreen(
                inputText = backStackEntry.arguments?.getString("keyWord") ?: "",
                onItemClick = { repositoryItem ->
                    val repositoryJson = Uri.encode(Gson().toJson(repositoryItem))
                    navController.navigateSingleTopTo("${GitHubAppScreens.DetailScreen.name}/$repositoryJson")
                }
            )
        }
        composable(
            route = "${GitHubAppScreens.DetailScreen.name}/{repositoryJson}",
            arguments = listOf(navArgument("repositoryJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val repositoryJson = backStackEntry.arguments?.getString("repositoryJson")
            val repositoryItem = Gson().fromJson(repositoryJson, RepositoryItem::class.java)

            DetailScreen(repositoryItem = repositoryItem)
        }
    }
}

private fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) {
        launchSingleTop = true
        restoreState = true
    }
