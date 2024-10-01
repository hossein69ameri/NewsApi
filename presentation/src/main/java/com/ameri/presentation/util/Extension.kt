package com.ameri.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Scale
import java.text.SimpleDateFormat
import java.util.Locale

fun String.formatDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.getDefault())
    val outputFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
    return try {
        val date = inputFormat.parse(this)
        date?.let { outputFormat.format(it) } ?: "Invalid Date"
    } catch (e: Exception) {
        "Invalid Date"
    }
}

@Composable
fun String.asyncImagePainter(): AsyncImagePainter {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(data = this)  // `this` refers to the String instance, i.e., the imageUrl
            .diskCachePolicy(CachePolicy.ENABLED)
            .memoryCachePolicy(CachePolicy.ENABLED)
            .apply(block = fun ImageRequest.Builder.() { scale(Scale.FILL) })
            .build()
    )
    return painter
}