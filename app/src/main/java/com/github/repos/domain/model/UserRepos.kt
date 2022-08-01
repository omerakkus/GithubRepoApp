package com.github.repos.domain.model

/**
 * @author omerakkus
 * @since 07/28/2022
 */

data class UserRepos(
    val repoName: String?,
    val ownerName: String?,
    val avatarUrl: String?,
    val forks_count: Int?,
    val language: String?,
    val default_branch:String?,
    val visibility:String?,
    val stargazers_count:Int?,
    val watchers_count: Int?,
    val full_name:String?

)