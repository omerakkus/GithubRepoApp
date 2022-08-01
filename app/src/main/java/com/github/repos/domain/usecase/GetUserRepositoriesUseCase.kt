package com.github.repos.domain.usecase

import com.github.repos.domain.model.UserRepos
import com.github.repos.domain.repository.RepoRepository
import com.github.repos.domain.util.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * @author omerakkus
 * @since 07/29/2022
 */

class GetUserRepositoriesUseCase @Inject constructor
    (private val repoRepository: RepoRepository){
    operator fun invoke(username:String): Flow<ResponseState<List<UserRepos>>> = flow {
        try {
            emit(ResponseState.Loading<List<UserRepos>>())
            val userRepos = repoRepository.getUserRepositories(username).map {
                it.toUserRepos()
            }
            emit(ResponseState.Success<List<UserRepos>>(userRepos))
        } catch (e: HttpException) {
            emit(ResponseState.Error<List<UserRepos>>(e.localizedMessage ?: "An Unexpected Error"))
        } catch (e: IOException) {
            emit(ResponseState.Error<List<UserRepos>>("Internet Error"))
        }
    }
}