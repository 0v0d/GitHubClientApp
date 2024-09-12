package com.example.githubclientapp.ui.screen

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.githubclientapp.R
import com.example.githubclientapp.ui.theme.AppTheme

@Composable
fun ErrorContent(
    @StringRes errorMessage: Int,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = errorMessage),
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedButton(
            onClick = onRetry
        ) {
            Text(
                text = "Retry",
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Suppress("UnusedPrivateMember")
@Composable
@Preview(showBackground = true)
private fun ErrorContentPreview() {
    AppTheme {
        ErrorContent(
            errorMessage = R.string.error_message,
            onRetry = {}
        )
    }
}
