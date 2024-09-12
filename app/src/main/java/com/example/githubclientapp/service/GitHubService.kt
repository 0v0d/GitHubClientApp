package com.example.githubclientapp.service

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpHeaders

class GitHubService(
    private val baseUrl: String,
    private val httpClient: HttpClient
) {
    suspend fun searchRepositories(query: String): String {
        return httpClient.get {
            url(baseUrl)
            parameter("q", query)
            header(HttpHeaders.Accept, "application/vnd.github.v3+json")
        }.bodyAsText()
    }
}
