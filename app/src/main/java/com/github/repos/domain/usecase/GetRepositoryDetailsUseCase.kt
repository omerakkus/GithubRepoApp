package com.github.repos.domain.usecase

import com.github.repos.domain.model.RepoDetails
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

class GetRepositoryDetailsUseCase @Inject constructor
    (private val repoRepository: RepoRepository) {

        operator fun invoke(username:String, repo:String): Flow<ResponseState<RepoDetails>> = flow {
            try{
                emit(ResponseState.Loading())
                val repoDetails = repoRepository.getRepositoryDetails(username = username, repo = repo)
                    .toRepositoryDetails()
                emit(ResponseState.Success(repoDetails))
            } catch (e: HttpException) {
                emit(ResponseState.Error<RepoDetails>(e.localizedMessage ?: "An Unexpected Error"))
            } catch (e: IOException) {
                emit(ResponseState.Error<RepoDetails>("Internet Error"))
            }
        }
}