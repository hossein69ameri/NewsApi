package com.ameri.local.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ameri.local.utils.NEWS_TABLE

@Entity(tableName = NEWS_TABLE)
data class NewsEntity(
    @PrimaryKey
    val uuid: String,
    val title: String?,
    val description: String?,
    val keywords: String?,
    val snippet: String?,
    val url: String?,
    val imageUrl: String?,
    val language: String?,
    val publishedAt: String?,
    val source: String?,
    val locale: String?,
    val table: String?
)
