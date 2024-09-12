package com.example.githubclientapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class InputViewModel : ViewModel() {
    private val _inputText = mutableStateOf("")
    val inputText: State<String> = _inputText

    fun updateInputText(newText: String) {
        _inputText.value = newText
    }
}
