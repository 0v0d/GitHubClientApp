package com.example.feature.utils

import androidx.compose.ui.graphics.Color.Companion.Gray
import com.example.feature.theme.languageColors

object ColorUtils {
    fun getColorForLanguage(language: String) = languageColors[language.lowercase()] ?: Gray
}
