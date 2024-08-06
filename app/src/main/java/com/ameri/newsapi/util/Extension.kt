package com.ameri.newsapi.util

// Extension function for String class
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun String.formatDate(): String {
    // Define the input format (ISO 8601 in this case)
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'", Locale.getDefault())

    // Define the output format for "Jan 3, 2024"
    val outputFormat = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())

    return try {
        // Parse the timestamp into a Date object
        val date = inputFormat.parse(this)
        // Format the Date object to the desired output
        date?.let { outputFormat.format(it) } ?: "Invalid Date"
    } catch (e: Exception) {
        // Handle parsing errors
        "Invalid Date"
    }
}