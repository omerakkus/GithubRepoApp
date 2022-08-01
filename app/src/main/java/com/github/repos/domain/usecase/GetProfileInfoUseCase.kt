package com.github.repos.domain.usecase

import com.github.repos.domain.model.UserInfo
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

class GetProfileInfoUseCase @Inject constructor(val repoRepository: RepoRepository){
    operator fun invoke(username:String): Flow<ResponseState<UserInfo>> = flow {
        try{
            emit(ResponseState.Loading())
            val userProfileInfo = repoRepository.getUserProfileInfo(username = username).toUserProfileInfo()
            emit(ResponseState.Success(userProfileInfo))

        }catch (e:HttpException){
            emit(ResponseState.Error<UserInfo>(e.localizedMessage ?: "An Unexpected Error"))
        }catch (e:IOException){
            emit(ResponseState.Error<UserInfo>("Internet Error"))
        }
    }
}