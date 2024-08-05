package com.ameri.newsapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ameri.newsapi.data.models.ResponseData
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

    private val _topHeadlinesState = MutableStateFlow<NetworkRequest<ResponseData>>(NetworkRequest.Loading())
    val topHeadlinesState = _topHeadlinesState.asStateFlow()

    fun getEverythingData() = viewModelScope.launch {
        _topHeadlinesState.value = NetworkRequest.Loading()
        _topHeadlinesState.value = NetworkResponse(repository.getEverythingData()).safeApiCall()
    }
}