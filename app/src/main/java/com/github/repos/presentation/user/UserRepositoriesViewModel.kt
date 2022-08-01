package com.github.repos.presentation.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.repos.domain.model.UserInfo
import com.github.repos.domain.model.UserRepos
import com.github.repos.domain.usecase.GetProfileInfoUseCase
import com.github.repos.domain.usecase.GetUserRepositoriesUseCase
import com.github.repos.domain.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author omerakkus
 * @since 07/30/2022
 */

@HiltViewModel
class UserRepositoriesViewModel @Inject constructor(
   private val getUserRepositoriesUseCase: GetUserRepositoriesUseCase,
   private val getUserProfileInfoUserInfoUseCase: GetProfileInfoUseCase
): ViewModel() {

   private val _userRepositories =MutableLiveData<ResponseState<List<UserRepos>>>()
    val userRepositories: LiveData<ResponseState<List<UserRepos>>>
    get() = _userRepositories

    private val _userProfileInfo =MutableLiveData<ResponseState<UserInfo>>()
    val userProfileInfo: LiveData<ResponseState<UserInfo>>
        get() = _userProfileInfo


   fun getUserRepositories(username:String) =viewModelScope.launch(Dispatchers.IO) {
       getUserRepositoriesUseCase(username = username).collect {
           _userRepositories.postValue(it)
       }
   }

   fun getUserProfileInfo(username: String) = viewModelScope.launch(Dispatchers.IO) {
       getUserProfileInfoUserInfoUseCase(username = username).collect {
           _userProfileInfo.postValue(it)
       }
   }
}