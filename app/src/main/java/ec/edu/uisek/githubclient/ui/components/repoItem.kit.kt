package ec.edu.uisek.githubclient.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ec.edu.uisek.githubclient.models.GithubUser
import ec.edu.uisek.githubclient.models.Repository
import ec.edu.uisek.githubclient.ui.theme.GithubClientTheme
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*

import androidx.compose.foundation.clickable

@Composable
fun RepoItem(
    repository: Repository,
    onDeleteClick: () -> Unit,
    onClick: () -> Unit

) {
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->

            if (value == SwipeToDismissBoxValue.EndToStart) {
                onDeleteClick()
                true
            } else {
                false
            }
        }
    )

    SwipeToDismissBox(
        state = dismissState,

        backgroundContent = {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                contentAlignment = Alignment.CenterEnd
            ) {

                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        },

        enableDismissFromStartToEnd = false,
        enableDismissFromEndToStart = true
    ){
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                onClick()
             },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = repository.owner.avatarUrl,
                contentDescription = repository.name,
                modifier = Modifier.size(48.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = repository.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                if (!repository.description.isNullOrBlank()) {
                    Text(
                        text = repository.description,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 2
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                if (!repository.language.isNullOrBlank()) {
                    Text(
                        text = repository.language,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2
                    )
                }
                /*IconButton(
                    onClick = onDeleteClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Eliminar",
                        tint = MaterialTheme.colorScheme.error
                    )
                }*/

            }
        }
    }
    }
}

@Preview(showBackground = true)
@Composable
fun RepoItemPreview1() {
    GithubClientTheme {
        val repository = Repository(
            id = "1",
            name = "Laboratorio 3",
            owner = GithubUser(
                id = "1",
                login = "CarlosCampos",
                avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4"
            ),
            description = "Hecho con Jetpack Compose.",
            language = "Kotlin"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RepoItemPreview2() {
    GithubClientTheme {

    }
}
