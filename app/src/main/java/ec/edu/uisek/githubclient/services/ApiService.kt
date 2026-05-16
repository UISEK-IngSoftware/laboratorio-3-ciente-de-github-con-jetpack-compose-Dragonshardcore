package ec.edu.uisek.githubclient.services

import ec.edu.uisek.githubclient.models.Repository
import ec.edu.uisek.githubclient.models.RepositoryPayload
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.Response
import retrofit2.http.PATCH

interface ApiService {
    @GET( value ="user/repos")
    suspend fun getRepository(
        @Query( value ="sort") sort: String = "created",
        @Query( value ="direction") direction: String = "desc",
        @Query( value ="affiliation") affiliation: String = "owner",
        @Query( value ="t") t: String = "${System.currentTimeMillis()}"
    ): List<Repository>

    @POST( value ="user/repos")
    suspend fun createRepository(
        @Body repository: RepositoryPayload
    ): Repository

    @DELETE("repos/{owner}/{repo}")
    suspend fun deleteRepo(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<Unit>

    @PATCH("repos/{owner}/{repo}")
    suspend fun updateRepo(
        @Path("owner") owner: String,
        @Path("repo") repo: String,

        @Body body: Map<String, String>
    ): Repository
}


