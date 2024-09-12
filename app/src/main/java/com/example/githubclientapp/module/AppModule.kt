package com.example.githubclientapp.module

import android.util.LruCache
import com.example.githubclientapp.model.APIGitHubResponse
import com.example.githubclientapp.repository.GitHubRepository
import com.example.githubclientapp.repository.GitHubRepositoryImpl
import com.example.githubclientapp.service.GitHubService
import com.example.githubclientapp.usecase.SearchRepositoriesUseCase
import com.example.githubclientapp.viewmodel.InputViewModel
import com.example.githubclientapp.viewmodel.RepositoryListViewModel
import com.google.gson.GsonBuilder
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

private const val TIMEOUT = 20_000

private val networkModule = module {
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
        GitHubService(
            baseUrl = "https://api.github.com/search/repositories",
            httpClient = get()
        )
    }
}

private val cacheModule = module {
    single { LruCache<String, APIGitHubResponse>(5) }
}

private val gsonModule = module {
    single { GsonBuilder().create() }
}

private val repositoryModule = module {
    single<GitHubRepository> { GitHubRepositoryImpl(get(), get(), get()) }
}

private val searchRepositoriesUseCaseModule = module {
    factory { SearchRepositoriesUseCase(get()) }
}

private val repositoryListViewModel = module {
    viewModel { RepositoryListViewModel(get()) }
}

private val inputViewModel = module {
    viewModel { InputViewModel() }
}

val appModule = listOf(
    inputViewModel,
    repositoryModule,
    cacheModule,
    gsonModule,
    networkModule,
    repositoryListViewModel,
    searchRepositoriesUseCaseModule
)
