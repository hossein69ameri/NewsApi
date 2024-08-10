package com.ameri.newsapi.ui.home.food_section

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ameri.newsapi.data.models.ResponseData
import com.ameri.newsapi.util.asyncImagePainter
import com.ameri.newsapi.util.formatDate
import com.ameri.newsapi.util.theme.AntiFlashWhite
import com.ameri.newsapi.util.theme.CoolGrey

@Composable
fun FoodItem(item: ResponseData.Data) {
    Box(
        modifier = Modifier
            .width(300.dp)
            .height(200.dp),
    ) {
        val painter = item.imageUrl.orEmpty().asyncImagePainter()
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 18.dp),
            shape = RoundedCornerShape(18.dp),
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
                .padding(start = 12.dp, bottom = 12.dp, end = 20.dp),
            verticalArrangement = Arrangement.Bottom,
        ) {
            Text(
                text = item.title.orEmpty(),
                fontSize = 16.sp,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = AntiFlashWhite
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "${
                    item.publishedAt.orEmpty()
                        .formatDate()
                } by ${
                    item.source.orEmpty()
                }",
                style = MaterialTheme.typography.bodySmall,
                color = CoolGrey
            )
        }
    }

}


@Preview
@Composable
fun FoodItemPreview() {
    FoodItem(ResponseData.Data())
}