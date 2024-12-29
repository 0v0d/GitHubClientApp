package com.example.feature.di

import com.example.domain.di.domainModules
import com.example.feature.viewmodel.InputViewModel
import com.example.feature.viewmodel.RepositoryListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

private val repositoryListViewModel = module {
    viewModel { RepositoryListViewModel(get()) }
}

private val inputViewModel = module {
    viewModel { InputViewModel() }
}

val featureModules = listOf(
    repositoryListViewModel,
    inputViewModel
) + domainModules