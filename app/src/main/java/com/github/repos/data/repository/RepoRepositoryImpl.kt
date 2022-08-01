package com.github.repos.data.repository

import com.github.repos.data.dto.repoDetailsDto.RepositoryDetailsDto
import com.github.repos.data.dto.allReposDto.AllRepositoriesDto
import com.github.repos.data.dto.userProfileDto.UserProfileDto
import com.github.repos.data.dto.userProfileDto.UserReposDto
import com.github.repos.data.framework.remote.RepoService
import com.github.repos.domain.repository.RepoRepository
import javax.inject.Inject

/**
 * @author omerakkus
 * @since 07/28/2022
 */

class RepoRepositoryImpl @Inject constructor(
    private val api: RepoService
) : RepoRepository {
    override suspend fun getAllRepositories(): AllRepositoriesDto {
        return api.getAllRepositories()
    }

    override suspend fun getRepositoryDetails(username:String,repo:String): RepositoryDetailsDto {
        return api.getRepositoryDetails(username = username, repo = repo)
    }

    override suspend fun getUserRepositories(username: String): UserReposDto {
        return api.getUserRepositories(username = username)
    }

    override suspend fun getUserProfileInfo(username: String): UserProfileDto {
        return api.getUserProfileInfo(username = username)
    }
}