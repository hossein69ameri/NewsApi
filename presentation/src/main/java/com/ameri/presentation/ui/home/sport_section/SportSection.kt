package com.ameri.presentation.ui.home.sport_section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
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
import com.ameri.domain.models.ResponseData
import com.ameri.presentation.ui.component.LoadingView
import com.ameri.presentation.util.constant.SPORTS
import com.ameri.presentation.util.networkUtil.NetworkRequest
import com.ameri.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SportSection(viewModel: NewsViewModel? = hiltViewModel()) {
    var popularityList by remember { mutableStateOf<List<ResponseData.Data>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }

    LaunchedEffect(Dispatchers.Main) {
        viewModel?.sportsState?.collectLatest { result ->
            when (result) {
                is NetworkRequest.Error -> {
                    loading = false
                }

                is NetworkRequest.Loading -> {
                    loading = true
                }

                is NetworkRequest.Success -> {
                    result.data?.let { sports ->
                        popularityList = sports.data!!
                    }
                    loading = false
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (loading) {
            LoadingView()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(modifier = Modifier
                    .padding(start = 20.dp),
                    text = SPORTS,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(modifier = Modifier.height(17.dp))
                LazyRow(contentPadding = PaddingValues(start = 20.dp)) {
                    items(popularityList) { item ->
                        SportItem(item = item)
                    }
                }
            }

        }
    }
}