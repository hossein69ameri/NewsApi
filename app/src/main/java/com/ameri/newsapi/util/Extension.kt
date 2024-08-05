package com.ameri.newsapi.util

import java.text.SimpleDateFormat
import java.util.Locale

// Extension function for String class
fun String.formatDate(): String {
    // Define the input format (ISO 8601 in this case)
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())

    // Define the output format for "Jan 3, 2024"
    val outputFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())

    return try {
        // Parse the timestamp into a Date object
        val date = inputFormat.parse(this) // 'this' refers to the calling String
        // Format the Date object to the desired output
        date?.let { outputFormat.format(it) } ?: "Invalid Date"
    } catch (e: Exception) {
        // Handle parsing errors
        "Invalid Date"
    }
}