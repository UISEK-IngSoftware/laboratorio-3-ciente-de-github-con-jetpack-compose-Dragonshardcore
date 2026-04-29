package ec.edu.uisek.githubclient.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ec.edu.uisek.githubclient.ui.theme.GithubClientTheme

@Composable
fun RepoItem(
    repoName: String,
    repoDescription: String?,
    repoLanguage: String?,
    repoImage: String
) {
    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = repoImage,
                contentDescription = "Repo Image",
                modifier = Modifier.size(48.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = repoName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                if (!repoDescription.isNullOrBlank()) {
                    Text(
                        text = repoDescription,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 2
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                if (!repoLanguage.isNullOrBlank()) {
                    Text(
                        text = repoLanguage,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepoItemPreview1() {
    GithubClientTheme {
        RepoItem(
            repoName = "Laboratorio 3",
            repoDescription = "Hecho con Jetpack Compose.",
            repoLanguage = "Kotlin",
            repoImage = "https://avatars.githubusercontent.com/u/1?v=4"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RepoItemPreview2() {
    GithubClientTheme {
        RepoItem(
            repoName = "Laboratory 3",
            repoDescription = "Jetpack Compose",
            repoLanguage = "Kotlin",
            repoImage = "https://avatars.githubusercontent.com/u/1?v=4"
        )
    }
}
