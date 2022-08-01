package com.github.repos.domain.repository

import com.github.repos.data.dto.repoDetailsDto.RepositoryDetailsDto
import com.github.repos.data.dto.allReposDto.AllRepositoriesDto
import com.github.repos.data.dto.userProfileDto.UserProfileDto
import com.github.repos.data.dto.userProfileDto.UserReposDto

/**
 * @author omerakkus
 * @since 07/282022
 */

interface RepoRepository {
    suspend fun getAllRepositories(): AllRepositoriesDto
    suspend fun getRepositoryDetails(username:String,repo:String): RepositoryDetailsDto
    suspend fun getUserRepositories(username:String): UserReposDto
    suspend fun getUserProfileInfo(username:String): UserProfileDto
}