package com.github.repos.domain.model


/**
 * @author omerakkus
 * @since 07/28/2022
 */

data class AllRepos(
    val repoName: String,
    val ownerName: String,
    val avatarUrl: String,
    val fullName: String
)