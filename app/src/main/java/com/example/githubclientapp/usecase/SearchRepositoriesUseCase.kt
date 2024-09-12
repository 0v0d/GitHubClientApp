package com.example.githubclientapp.usecase

import com.example.githubclientapp.repository.GitHubRepository

class SearchRepositoriesUseCase(private val repository: GitHubRepository) {
    suspend operator fun invoke(query: String) = repository.getRepositories(query)
}
