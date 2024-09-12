package com.example.githubclientapp

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.githubclientapp.navigation.NavigationGraph

@Composable
fun GitHubApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: GitHubAppScreens.InputScreen.name

    val currentScreen = when {
        currentRoute.startsWith(GitHubAppScreens.InputScreen.name) -> GitHubAppScreens.InputScreen
        currentRoute.startsWith(GitHubAppScreens.RepositoryListScreen.name) -> GitHubAppScreens.RepositoryListScreen
        currentRoute.startsWith(GitHubAppScreens.DetailScreen.name) -> GitHubAppScreens.DetailScreen
        else -> GitHubAppScreens.InputScreen
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            GitHubAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        NavigationGraph(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GitHubAppBar(
    currentScreen: GitHubAppScreens,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                    )
                }
            }
        }
    )
}

enum class GitHubAppScreens(@StringRes val title: Int) {
    InputScreen(R.string.input_screen_title),
    RepositoryListScreen(R.string.list_screen_title),
    DetailScreen(R.string.detail_screen_title),
}
