package com.example.githubclientapp.model

import kotlinx.serialization.Serializable

@Serializable
data class RepositoryItem(
    val id: Long,
    val name: String,
    val fullName: String,
    val owner: OwnerItem,
    val htmlUrl: String,
    val description: String?,
    val language: String?,
    val stargazersCount: String,
    val watchersCount: String,
    val forksCount: String,
    val openIssuesCount: String
)

@Serializable
data class OwnerItem(
    val login: String,
    val avatarUrl: String,
    val htmlUrl: String
)
