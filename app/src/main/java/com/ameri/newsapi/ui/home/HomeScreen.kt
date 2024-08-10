package com.ameri.newsapi.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ameri.newsapi.data.models.ResponseData
import com.ameri.newsapi.ui.component.LoadingView
import com.ameri.newsapi.ui.component.PageIndicator
import com.ameri.newsapi.ui.home.food_section.FoodSection
import com.ameri.newsapi.ui.home.sport_section.SportSection
import com.ameri.newsapi.util.network.NetworkRequest
import com.ameri.newsapi.viewmodel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel? = hiltViewModel()) {
    var list by remember { mutableStateOf(ResponseData()) }
    val pagerState = rememberPagerState(pageCount = { list.data?.size ?: 0 })
    var imageUrl by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel?.getEverythingData()
        viewModel?.getSportsData()
        viewModel?.getFoodiesData()
        while (true) {
            delay(5000)
            if (pagerState.pageCount > 0) {
                val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
                pagerState.animateScrollToPage(nextPage)
            }
        }
    }
    LaunchedEffect(Dispatchers.Main) {
        viewModel?.topStoriesState?.collectLatest { result ->
            when (result) {
                is NetworkRequest.Error -> {
                    loading = false
                }

                is NetworkRequest.Loading -> {
                    loading = true
                }

                is NetworkRequest.Success -> {
                    result.data?.let { topStories ->
                        list = topStories
                    }
                    loading = false
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (loading) {
            LoadingView()
        } else {
            Spacer(modifier = Modifier.height(8.dp))
            TopSlider(pagerState, imageUrl, list)
            Spacer(modifier = Modifier.height(10.dp))
            PageIndicator(
                pageCount = pagerState.pageCount,
                currentPage = pagerState.currentPage,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(20.dp))
            SportSection()
            Spacer(modifier = Modifier.height(20.dp))
            FoodSection()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen(navController = rememberNavController(), null)
}