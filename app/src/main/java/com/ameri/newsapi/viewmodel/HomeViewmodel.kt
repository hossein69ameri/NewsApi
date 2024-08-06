package com.ameri.newsapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ameri.newsapi.data.models.ResponseTopStories
import com.ameri.newsapi.data.repository.HomeRepository
import com.ameri.newsapi.util.network.NetworkRequest
import com.ameri.newsapi.util.network.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    private val _topStoriesState = MutableStateFlow<NetworkRequest<ResponseTopStories>>(NetworkRequest.Loading())
    val topStoriesState = _topStoriesState.asStateFlow()

    fun getEverythingData() = viewModelScope.launch {
        _topStoriesState.value = NetworkRequest.Loading()
        _topStoriesState.value = NetworkResponse(repository.getTopStoriesData()).safeApiCall()
    }
}