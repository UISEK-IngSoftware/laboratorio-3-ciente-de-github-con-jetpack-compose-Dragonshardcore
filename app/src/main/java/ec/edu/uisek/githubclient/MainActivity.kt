package ec.edu.uisek.githubclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import ec.edu.uisek.githubclient.models.Repository
import ec.edu.uisek.githubclient.services.AuthService
import ec.edu.uisek.githubclient.services.RetrofitClient
import ec.edu.uisek.githubclient.ui.screens.LoginForm
import ec.edu.uisek.githubclient.ui.screens.RepoForm
import ec.edu.uisek.githubclient.ui.screens.RepoList
import ec.edu.uisek.githubclient.ui.theme.GithubClientTheme
import ec.edu.uisek.githubclient.viewmodels.RepoListViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RetrofitClient.init(this)
        enableEdgeToEdge()
        val authService = AuthService(this)

        setContent {
            GithubClientTheme {
                val listViewModel: RepoListViewModel = viewModel()

                val loggedIn = authService.isLoggedIn()

                var currentScreen by remember {
                    mutableStateOf(
                        if (loggedIn) "repo_list" else "login"
                    )
                }

                var selectedRepo by remember {
                    mutableStateOf<Repository?>(null)
                }

                when (currentScreen) {
                    "login" -> LoginForm(
                        onLoginSuccess = {
                            currentScreen = "repo_list"
                        }
                    )

                    "repo_list" -> RepoList(
                        viewModel = listViewModel,
                        onNavigateToForm = {
                            selectedRepo = null
                            currentScreen = "repo_form"
                        },
                        onEditRepo = { repo ->
                            selectedRepo = repo
                            currentScreen = "repo_form"
                        },
                        onLogout = {
                            authService.logout()
                            currentScreen = "login"
                        }
                    )

                    "repo_form" -> RepoForm(
                        repository = selectedRepo,
                        onSaveSuccess = {
                            listViewModel.fetchRepos()
                            currentScreen = "repo_list"
                        },
                        onBackClick = {
                            currentScreen = "repo_list"
                        }
                    )
                }
            }
        }
    }
}