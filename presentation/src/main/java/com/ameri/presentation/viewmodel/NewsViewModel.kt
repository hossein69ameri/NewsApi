package com.ameri.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ameri.domain.model.NewsData
import com.ameri.domain.repository.NewsRepository
import com.ameri.presentation.util.networkUtil.NetworkRequest
import com.ameri.remote.ALL
import com.ameri.remote.Foodies_CATEGORY
import com.ameri.remote.SPORTS_CATEGORY
import com.ameri.remote.TOP
import com.ameri.remote.Techs_CATEGORY
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
    //Sports
    private val _sportsState = MutableStateFlow<NetworkRequest<NewsData>>(NetworkRequest.Loading())
    val sportsState = _sportsState.asStateFlow()
    //Food
    private val _foodState = MutableStateFlow<NetworkRequest<NewsData>>(NetworkRequest.Loading())
    val foodState = _foodState.asStateFlow()
    //Tech
    private val _techState = MutableStateFlow<NetworkRequest<NewsData>>(NetworkRequest.Loading())
    val techState = _techState.asStateFlow()

    fun getEverythingData() = viewModelScope.launch {
        _topStoriesState.value = NetworkRequest.Loading()
        repository.loadNewsByCategory(TOP).collect { cachedData ->
            _topStoriesState.value = if (cachedData.isEmpty()) {
                NetworkRequest.Error("No Cached Data")
            } else {
                NetworkRequest.Success(NewsData(data = cachedData))
            }
        }
    }

    fun getSportsData() = viewModelScope.launch {
        _sportsState.value = NetworkRequest.Loading()
        repository.loadNewsByCategory(ALL,SPORTS_CATEGORY).collect { cachedData ->
            _sportsState.value = if (cachedData.isEmpty()) {
                NetworkRequest.Error("No Cached Data")
            } else {
                NetworkRequest.Success(NewsData(data = cachedData))
            }
        }
    }

    fun getFoodiesData() = viewModelScope.launch {
        _foodState.value = NetworkRequest.Loading()
        repository.loadNewsByCategory(ALL,Foodies_CATEGORY).collect { cachedData ->
            _foodState.value = if (cachedData.isEmpty()) {
                NetworkRequest.Error("No Cached Data")
            } else {
                NetworkRequest.Success(NewsData(data = cachedData))
            }
        }
    }

    fun getTechnologyData() = viewModelScope.launch {
        _techState.value = NetworkRequest.Loading()
        repository.loadNewsByCategory(ALL,Techs_CATEGORY).collect { cachedData ->
            _techState.value = if (cachedData.isEmpty()) {
                NetworkRequest.Error("No Cached Data")
            } else {
                NetworkRequest.Success(NewsData(data = cachedData))
            }
        }
    }

    fun refreshData() = viewModelScope.launch {
        repository.getData(TOP)
        repository.getData(ALL, category = SPORTS_CATEGORY)
        repository.getData(ALL, category = Foodies_CATEGORY)
        repository.getData(ALL, category = Techs_CATEGORY)
    }
}