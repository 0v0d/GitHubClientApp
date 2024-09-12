package com.example.githubclientapp.util

import androidx.compose.ui.graphics.Color
import com.example.githubclientapp.ui.theme.languageColors

class Utility {
    fun getColorForLanguage(language: String) = languageColors[language.lowercase()] ?: Color.Gray
}
