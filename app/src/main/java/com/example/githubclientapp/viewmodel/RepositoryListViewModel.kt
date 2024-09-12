package com.example.githubclientapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubclientapp.model.RepositoryItem
import com.example.githubclientapp.model.toDomainModel
import com.example.githubclientapp.usecase.SearchRepositoriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RepositoryListViewModel(
    private val searchRepositoriesUseCase: SearchRepositoriesUseCase
) : ViewModel() {
    private val _repositories = MutableStateFlow<List<RepositoryItem>>(emptyList())
    val repositories = _repositories.asStateFlow()

    private val _loadingState = MutableStateFlow(true)
    val loadingState = _loadingState.asStateFlow()

    private lateinit var lastQuery: String

    fun searchRepositories(inputText: String) {
        lastQuery = inputText
        _loadingState.value = true
        viewModelScope.launch {
            try {
                val response = searchRepositoriesUseCase(inputText)
                if (response != null) {
                    _repositories.value = response.items.map { it.toDomainModel() }
                }
            } catch (e: Exception) {
                _repositories.value = emptyList()
            } finally {
                _loadingState.value = false
            }
        }
    }
}
