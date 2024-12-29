package com.example.network.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

private const val TIMEOUT = 20_000

val networkModule = module {
    single {
        HttpClient(Android) {
            engine {
                connectTimeout = TIMEOUT
                socketTimeout = TIMEOUT
            }
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true }, ContentType.Application.Json)
            }
        }
    }
    single {
        com.example.network.service.GitHubService(
            baseUrl = "https://api.github.com/search/repositories",
            httpClient = get()
        )
    }
}
