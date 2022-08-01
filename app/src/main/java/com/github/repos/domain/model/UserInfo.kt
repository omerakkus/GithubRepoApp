package com.github.repos.domain.model

/**
 * @author omerakkus
 * @since 07/28/2022
 */

data class UserInfo(
    val username:String,
    val avatarUrl: String,
    val name:String?,
    val email: String?,
    val company:String?,
    val location:String?,
    val followers:Int,
    val following:Int
)