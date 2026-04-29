package ec.edu.uisek.githubclient.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ec.edu.uisek.githubclient.ui.components.RepoItem
import ec.edu.uisek.githubclient.ui.theme.GithubClientTheme

@Composable
fun RepoList() {
    Column (
        modifier = Modifier
            .padding(top = 40.dp, start = 16.dp, end = 16.dp, bottom = 8.dp)

    ){
        RepoItem(
            repoName = "Laboratorio 3",
            repoDescription = "Hecho con Jetpack Compose.",
            repoLanguage = "Kotlin",
            repoImage = "https://avatars.githubusercontent.com/u/1?v=4"
        )
        RepoItem(
            repoName = "Laboratorio 4",
            repoDescription = "Hecho con Jetpack Next.",
            repoLanguage = "Swift",
            repoImage = "https://avatars.githubusercontent.com/u/1?v=4"
        )
        RepoItem(
            repoName = "Laboratorio 5",
            repoDescription = "Hecho con Jetpack DRF.",
            repoLanguage = "Python",
            repoImage = "https://avatars.githubusercontent.com/u/1?v=4"
        )
        RepoItem(
            repoName = "Laboratorio 6",
            repoDescription = "Hecho con Jetpack SFR.",
            repoLanguage = "Python",
            repoImage = "https://avatars.githubusercontent.com/u/1?v=4"
        )
    }
}
@Preview (showBackground = true)
@Composable
fun RepoItemPreview () {
    GithubClientTheme {
        RepoList()
    }
}