package com.example.feature

import androidx.annotation.StringRes

enum class AppScreens(@StringRes val title: Int) {
    InputScreen(R.string.input_screen_title),
    RepositoryListScreen(R.string.list_screen_title),
    DetailScreen(R.string.detail_screen_title),
}