package com.github.repos.domain.usecase

import com.github.repos.domain.model.AllRepos
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

class GetAllRepositoriesUseCase@Inject constructor
    (private val repoRepository: RepoRepository){

        operator fun invoke(): Flow<ResponseState<List<AllRepos>>> = flow {
            try{
                emit(ResponseState.Loading())
                val allRepos = repoRepository.getAllRepositories().map {
                    it.toAllRepos()
                }
                emit(ResponseState.Success(allRepos))

            }catch (e: HttpException){
                emit(ResponseState.Error<List<AllRepos>>(e.localizedMessage ?: "An Unexpected Error"))
            }catch (e:IOException){
                emit(ResponseState.Error<List<AllRepos>>("Internet Error"))
            }
        }
}