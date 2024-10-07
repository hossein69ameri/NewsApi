package com.ameri.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ameri.remote.models.ResponseData
import com.ameri.domain.repository.NewsRepository
import com.ameri.presentation.util.networkUtil.NetworkRequest
import com.ameri.presentation.util.networkUtil.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    //Top Stories
    private val _topStoriesState =
        MutableStateFlow<NetworkRequest<com.ameri.remote.models.ResponseData>>(NetworkRequest.Loading())
    val topStoriesState = _topStoriesState.asStateFlow()

    fun getEverythingData() = viewModelScope.launch {
        _topStoriesState.value = NetworkRequest.Loading()
        _topStoriesState.value = NetworkResponse(repository.getTopStoriesData()).safeApiCall()
    }

    //Sports
    private val _sportsState =
        MutableStateFlow<NetworkRequest<com.ameri.remote.models.ResponseData>>(NetworkRequest.Loading())
    val sportsState = _sportsState.asStateFlow()

    fun getSportsData() = viewModelScope.launch {
        _sportsState.value = NetworkRequest.Loading()
        _sportsState.value = NetworkResponse(repository.getSportsData()).safeApiCall()
    }

    //Food
    private val _foodState =
        MutableStateFlow<NetworkRequest<com.ameri.remote.models.ResponseData>>(NetworkRequest.Loading())
    val foodState = _foodState.asStateFlow()

    fun getFoodiesData() = viewModelScope.launch {
        _foodState.value = NetworkRequest.Loading()
        _foodState.value = NetworkResponse(repository.getFoodiesData()).safeApiCall()
    }

    //Tech
    private val _techState =
        MutableStateFlow<NetworkRequest<com.ameri.remote.models.ResponseData>>(NetworkRequest.Loading())
    val techState = _techState.asStateFlow()

    fun getTechnologyData() = viewModelScope.launch {
        _techState.value = NetworkRequest.Loading()
        _techState.value = NetworkResponse(repository.getTechnologyData()).safeApiCall()
    }
}