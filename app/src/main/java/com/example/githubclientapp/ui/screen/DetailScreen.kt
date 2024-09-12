package com.example.githubclientapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.CallSplit
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.githubclientapp.model.OwnerItem
import com.example.githubclientapp.model.RepositoryItem
import com.example.githubclientapp.util.Utility

@Composable
fun DetailScreen(
    repositoryItem: RepositoryItem,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = repositoryItem.name,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = repositoryItem.owner.avatarUrl,
                contentDescription = "Owner Avatar",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = repositoryItem.owner.login)
        }

        Spacer(modifier = Modifier.height(16.dp))

        repositoryItem.description?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        repositoryItem.language?.let {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Code,
                    contentDescription = "Language"
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = it,
                    color = Utility().getColorForLanguage(it)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            StatisticItem(Icons.Default.Star, repositoryItem.stargazersCount, "Stars")
            StatisticItem(Icons.Default.RemoveRedEye, repositoryItem.watchersCount, "Watchers")
            StatisticItem(Icons.AutoMirrored.Filled.CallSplit, repositoryItem.forksCount, "Forks")
            StatisticItem(Icons.Default.ErrorOutline, repositoryItem.openIssuesCount, "Issues")
        }
    }
}

@Composable
private fun StatisticItem(
    icon: ImageVector,
    count: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(imageVector = icon, contentDescription = label)
        Text(text = count)
        Text(text = label, style = MaterialTheme.typography.bodySmall)
    }
}

@Suppress("UnusedPrivateMember")
@Preview(showBackground = true)
@Composable
private fun PreviewDetailScreen() {
    DetailScreen(
        repositoryItem = RepositoryItem(
            id = 123456789,
            name = "Hello-World",
            fullName = "octocat/Hello-World",
            owner = OwnerItem(
                login = "octocat",
                avatarUrl = "https://avatars.githubusercontent.com/u/583231?v=4",
                htmlUrl = "https://github.com/octocat"
            ),
            htmlUrl = "https://github.com/octocat/Hello-World",
            description = "This is your first repository",
            language = "Kotlin",
            stargazersCount = "1500",
            watchersCount = "1500",
            forksCount = "300",
            openIssuesCount = "42"
        ),
    )
}
