package com.ameri.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "response_data")
data class NewsEntity(
    @PrimaryKey(autoGenerate = false)
    val uuid: String, // The primary key
    val title: String?,
    val description: String?,
    val keywords: String?,
    val snippet: String?,
    val url: String?,
    val imageUrl: String?,
    val language: String?,
    val publishedAt: String?,
    val source: String?,
    val locale: String?
)
