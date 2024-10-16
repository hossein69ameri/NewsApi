package com.ameri.presentation.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ameri.domain.model.NewsData
import com.ameri.presentation.R
import com.ameri.presentation.util.asyncImagePainter
import com.ameri.presentation.util.formatDate
import com.ameri.presentation.util.theme.AntiFlashWhite
import com.ameri.presentation.util.theme.CoolGrey


@Composable
fun TopSlider(
    pagerState: PagerState,
    imageUrl: String,
    list: NewsData
) {
    var imageUrl1 = imageUrl
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
                imageUrl1 = list.data.orEmpty().getOrNull(currentPage)?.imageUrl.orEmpty()
                val painter = imageUrl1.asyncImagePainter()
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp),
                    shape = RoundedCornerShape(18.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                    border = BorderStroke(
                        width = 2.dp, color = MaterialTheme.colorScheme.secondary
                    ),
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
                        color = AntiFlashWhite,
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
                        color = CoolGrey,
                    )
                }
            }
        }
    }
}