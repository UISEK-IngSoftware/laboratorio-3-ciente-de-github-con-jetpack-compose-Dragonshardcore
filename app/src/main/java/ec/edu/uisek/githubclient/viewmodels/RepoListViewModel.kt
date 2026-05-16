package ec.edu.uisek.githubclient.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ec.edu.uisek.githubclient.models.Repository
import ec.edu.uisek.githubclient.services.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RepoListViewModel : ViewModel(){
    private val _repos = MutableStateFlow<List<Repository>>(emptyList())
    val repos: StateFlow<List<Repository>> = _repos.asStateFlow()
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMSG = MutableStateFlow<String?>(null)
    val errorMSG: StateFlow<String?> = _errorMSG.asStateFlow()
    private val apiService = RetrofitClient.apiService

    private val _isSuccess = MutableStateFlow(false)

    init {
        fetchRepos()
    }

    fun fetchRepos (){
        viewModelScope.launch {
            _isLoading.value = true
            _errorMSG.value = null
            try {
                val repos = RetrofitClient.apiService.getRepository()
                _repos.value = repos
                } catch (e: Exception) {
                    _errorMSG.value = "Error fetching repositories: ${e.localizedMessage}"
                } finally {
                    _isLoading.value = false
                }
            }
        }
    fun deleteRepo(owner: String, repo: String) {

        viewModelScope.launch {

            try {
                _isLoading.value = true
                apiService.deleteRepo(owner, repo)
                _isSuccess.value = true
            } catch (e: Exception) {
                _errorMSG.value = "Error al eliminar: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
                fetchRepos()
            }
        }
    }

    }

