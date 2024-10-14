package com.ameri.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ameri.domain.model.NewsData
import com.ameri.domain.repository.NewsRepository
import com.ameri.presentation.util.networkUtil.NetworkRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    //Top Stories
    private val _topStoriesState = MutableStateFlow<NetworkRequest<NewsData>>(NetworkRequest.Loading())
    val topStoriesState = _topStoriesState.asStateFlow()

    fun getEverythingData() = viewModelScope.launch {
        _topStoriesState.value = NetworkRequest.Loading()
        val data = repository.getTopStoriesData()
        if (data.data.isNullOrEmpty()) {
            _topStoriesState.value = NetworkRequest.Error("No Data")
        } else {
            _topStoriesState.value = NetworkRequest.Success(data)
        }
    }

    //Sports
    private val _sportsState = MutableStateFlow<NetworkRequest<NewsData>>(NetworkRequest.Loading())
    val sportsState = _sportsState.asStateFlow()

    fun getSportsData() = viewModelScope.launch {
        _sportsState.value = NetworkRequest.Loading()
        val data = repository.getSportsData()
        if (data.data.isNullOrEmpty()) {
            _sportsState.value = NetworkRequest.Error("No Data")
        } else {
            _sportsState.value = NetworkRequest.Success(data)
        }
    }

    //Food
    private val _foodState = MutableStateFlow<NetworkRequest<NewsData>>(NetworkRequest.Loading())
    val foodState = _foodState.asStateFlow()

    fun getFoodiesData() = viewModelScope.launch {
        _foodState.value = NetworkRequest.Loading()
        val data = repository.getFoodiesData()
        if (data.data.isNullOrEmpty()) {
            _foodState.value = NetworkRequest.Error("No Data")
        } else {
            _foodState.value = NetworkRequest.Success(data)
        }
    }

    //Tech
    private val _techState = MutableStateFlow<NetworkRequest<NewsData>>(NetworkRequest.Loading())
    val techState = _techState.asStateFlow()

    fun getTechnologyData() = viewModelScope.launch {
        _techState.value = NetworkRequest.Loading()
        val data = repository.getTechnologyData()
        if (data.data.isNullOrEmpty()) {
            _techState.value = NetworkRequest.Error("No Data")
        } else {
            _techState.value = NetworkRequest.Success(data)
        }
    }
}