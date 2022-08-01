package com.github.repos.domain.model

/**
 * @author omerakkus
 * @since 07/28/2022
 */

data class RepoDetails(
    val repoName:String,
    val ownerName: String,
    val avatar:String,
    val forksCount: Int,
    val language: String?,
    val default_branch: String
)