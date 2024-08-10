package com.ameri.newsapi.ui.home.tech_section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ameri.newsapi.data.models.ResponseData
import com.ameri.newsapi.ui.component.LoadingView
import com.ameri.newsapi.util.constant.FOODIES
import com.ameri.newsapi.util.constant.TECHNOLOGIES
import com.ameri.newsapi.util.network.NetworkRequest
import com.ameri.newsapi.viewmodel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@Composable
fun TechSection(viewModel: HomeViewModel? = hiltViewModel()) {
    var techList by remember { mutableStateOf<List<ResponseData.Data>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }

    LaunchedEffect(Dispatchers.Main) {
        viewModel?.techState?.collectLatest { result ->
            when (result) {
                is NetworkRequest.Error -> {
                    loading = false
                }

                is NetworkRequest.Loading -> {
                    loading = true
                }

                is NetworkRequest.Success -> {
                    result.data?.let { techs ->
                        techList = techs.data!!
                    }
                    loading = false
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (loading) {
            LoadingView()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp),
            ) {
                Text(
                    text = TECHNOLOGIES,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(17.dp))
                LazyRow() {
                    items(techList) { item ->
                        TechItem(item = item)
                    }
                }
            }

        }
    }
}