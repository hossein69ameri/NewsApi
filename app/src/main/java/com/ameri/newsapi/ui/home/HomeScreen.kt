package com.ameri.newsapi.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Scale
import com.ameri.newsapi.R
import com.ameri.newsapi.data.models.ResponseData
import com.ameri.newsapi.util.formatDate
import com.ameri.newsapi.util.network.NetworkRequest
import com.ameri.newsapi.util.theme.AntiFlashWhite
import com.ameri.newsapi.util.theme.CoolGrey
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .height(34.dp)
                        .width(34.dp),
                    color = MaterialTheme.colorScheme.tertiary
                )
            }
        } else {
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                modifier = Modifier
                    .width(100.dp)
                    .wrapContentHeight(),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.wrapContentSize(),
                    contentPadding = PaddingValues(horizontal = 8.dp),
                ) { currentPage ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        imageUrl = list.data.orEmpty().getOrNull(currentPage)?.imageUrl.orEmpty()
                        val painter = asyncImagePainter(imageUrl)
                        Card(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 10.dp),
                            shape = RoundedCornerShape(18.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                            border = BorderStroke(width = 2.dp, color = Color.White),
                        ) {
                            Image(
                                painter = painter,
                                contentDescription = "",
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .drawWithCache {
                                        val gradient = Brush.verticalGradient(
                                            colors = listOf(Color.Transparent, Color.Black),
                                            startY = size.height / 3,
                                            endY = size.height
                                        )
                                        onDrawWithContent {
                                            drawContent()
                                            drawRect(gradient, blendMode = BlendMode.Multiply)
                                        }
                                    },
                            )
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 25.dp, bottom = 12.dp, end = 20.dp),
                            verticalArrangement = Arrangement.Bottom,
                        ) {
                            Text(
                                text = list.data.orEmpty().getOrNull(currentPage)?.title.orEmpty(),
                                fontSize = 16.sp,
                                style = MaterialTheme.typography.titleMedium,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                color = AntiFlashWhite
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "${
                                    list.data?.getOrNull(currentPage)?.publishedAt.orEmpty()
                                        .formatDate()
                                } by ${
                                    list.data.orEmpty().getOrNull(currentPage)?.source.orEmpty()
                                }",
                                style = MaterialTheme.typography.bodySmall,
                                color = CoolGrey
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            PageIndicator(
                pageCount = pagerState.pageCount,
                currentPage = pagerState.currentPage,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(20.dp))
            SportSection()
        }
    }
}

@Composable
private fun asyncImagePainter(imageUrl: String): AsyncImagePainter {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(data = imageUrl)
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .apply(block = fun ImageRequest.Builder.() { scale(Scale.FILL) })
            .build()
    )
    return painter
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen(navController = rememberNavController(), null)
}