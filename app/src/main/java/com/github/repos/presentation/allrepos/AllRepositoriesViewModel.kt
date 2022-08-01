package com.github.repos.presentation.allrepos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.repos.domain.model.AllRepos
import com.github.repos.domain.usecase.GetAllRepositoriesUseCase
import com.github.repos.domain.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author omerakkus
 * @since 07/29/2022
 */

@HiltViewModel
class AllRepositoriesViewModel @Inject constructor(val allRepositoriesUseCase: GetAllRepositoriesUseCase)
    :ViewModel(){

    private val _allRepositories = MutableLiveData<ResponseState<List<AllRepos>>>()
    val allRepositories: LiveData<ResponseState<List<AllRepos>>>
        get() = _allRepositories


    fun getAllRepositories() =viewModelScope.launch(Dispatchers.IO) {
        allRepositoriesUseCase().collect{
            _allRepositories.postValue(it)
        }
    }
}