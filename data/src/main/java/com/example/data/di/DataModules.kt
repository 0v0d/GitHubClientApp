package com.example.data.di

import android.util.LruCache
import com.example.data.model.APIGitHubResponse
import com.example.data.repository.GitHubRepository
import com.example.data.repository.GitHubRepositoryImpl
import com.google.gson.GsonBuilder
import com.example.network.di.networkModule
import org.koin.dsl.module

private val repositoryModule = module {
    single<GitHubRepository> {
        GitHubRepositoryImpl(
            get(),
            get(),
            get()
        )
    }
}

private val gsonModule = module {
    single { GsonBuilder().create() }
}

private val cacheModule = module {
    single { LruCache<String, APIGitHubResponse>(5) }
}

val dataModules = listOf(
    networkModule,
    repositoryModule,
    gsonModule,
    cacheModule
)