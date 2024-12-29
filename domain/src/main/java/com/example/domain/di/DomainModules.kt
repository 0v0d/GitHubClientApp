package com.example.domain.di

import com.example.data.di.dataModules
import com.example.domain.usecase.SearchRepositoriesUseCase
import org.koin.dsl.module

private val searchRepositoriesUseCaseModule = module {
    factory { SearchRepositoriesUseCase(get()) }
}


// ドメイン層モジュールをリスト化（dataModulesを展開）
val domainModules = listOf(
    searchRepositoriesUseCaseModule
) + dataModules