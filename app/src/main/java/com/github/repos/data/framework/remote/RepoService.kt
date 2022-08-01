package com.github.repos.data.framework.remote

import com.github.repos.data.dto.repoDetailsDto.RepositoryDetailsDto
import com.github.repos.data.dto.allReposDto.AllRepositoriesDto
import com.github.repos.data.dto.userProfileDto.UserProfileDto
import com.github.repos.data.dto.userProfileDto.UserReposDto
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author omerakkus
 * @since 07/28/2022
 */

interface RepoService {

    @GET("repositories")
    suspend fun getAllRepositories(): AllRepositoriesDto

    @GET("repos/{username}/{repo}")
    suspend fun getRepositoryDetails(@Path("username")username:String,@Path("repo")repo:String): RepositoryDetailsDto

    @GET("users/{username}/repos")
    suspend fun getUserRepositories(@Path("username")username:String): UserReposDto

    @GET("users/{username}")
    suspend fun getUserProfileInfo(@Path("username")username: String): UserProfileDto

}