package com.example.githubclientapp.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class APIGitHubResponse(
    @SerializedName("items")
    val items: List<APIRepositoryItem>
)

@Serializable
data class APIRepositoryItem(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("owner")
    val owner: APIOwnerItem,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("language")
    val language: String?,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("watchers_count")
    val watchersCount: Int,
    @SerializedName("forks_count")
    val forksCount: Int,
    @SerializedName("open_issues_count")
    val openIssuesCount: Int
)

@Serializable
data class APIOwnerItem(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("html_url")
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
