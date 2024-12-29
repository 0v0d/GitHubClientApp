package com.example.domain.usecase

import com.example.data.repository.GitHubRepository
import com.example.domain.model.RepositoryItem
import com.example.domain.model.toDomainModel

class SearchRepositoriesUseCase(private val repository: GitHubRepository) {
    suspend operator fun invoke(query: String): List<RepositoryItem> {
        return repository.getRepositories(query)?.items
            ?.map { it.toDomainModel() }
            ?: emptyList()
    }
}
