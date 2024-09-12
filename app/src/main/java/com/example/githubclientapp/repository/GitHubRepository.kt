package com.example.githubclientapp.repository

import android.util.LruCache
import com.example.githubclientapp.model.APIGitHubResponse
import com.example.githubclientapp.service.GitHubService
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface GitHubRepository {
    suspend fun getRepositories(query: String): APIGitHubResponse?
}

class GitHubRepositoryImpl(
    private val service: GitHubService,
    private val cache: LruCache<String, APIGitHubResponse>,
    private val gson: Gson
) : GitHubRepository {

    override suspend fun getRepositories(query: String) = searchRepositories(query)

    private suspend fun searchRepositories(query: String): APIGitHubResponse? =
        withContext(Dispatchers.IO) {
            cache.get(query)?.let { return@withContext it }

            return@withContext try {
                val apiResponse =
                    gson.fromJson(service.searchRepositories(query), APIGitHubResponse::class.java)
                cache.put(query, apiResponse)
                apiResponse
            } catch (e: Exception) {
                null
            }
        }
}
