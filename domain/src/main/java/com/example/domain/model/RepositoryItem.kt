package com.example.domain.model

import com.example.data.model.APIOwnerItem
import com.example.data.model.APIRepositoryItem
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

fun APIRepositoryItem.toDomainModel() = RepositoryItem(
    id = id,
    name = name,
    fullName = fullName,
    owner = owner.toDomainModel(),
    htmlUrl = htmlUrl,
    description = description,
    language = language,
    stargazersCount = stargazersCount.toString(),
    watchersCount = watchersCount.toString(),
    forksCount = forksCount.toString(),
    openIssuesCount = openIssuesCount.toString()
)

fun APIOwnerItem.toDomainModel() = OwnerItem(
    login = login,
    avatarUrl = avatarUrl,
    htmlUrl = htmlUrl
)
