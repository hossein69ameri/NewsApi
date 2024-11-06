package com.ameri.mapper

import com.ameri.domain.model.NewsData
import com.ameri.local.database.NewsEntity

fun NewsEntity.toNewsData(table: String? = null): NewsData.Data = NewsData.Data(
    uuid = this.uuid,
    title = this.title,
    description = this.description,
    snippet = this.snippet,
    url = this.url,
    imageUrl = this.imageUrl,
    language = this.language,
    publishedAt = this.publishedAt,
    source = this.source,
    locale = this.locale,
    table = table,
)

fun NewsData.Data.toNewsEntity(table: String? = null): NewsEntity = NewsEntity(
    uuid = this.uuid ?: "",
    title = this.title,
    description = this.description,
    snippet = this.snippet,
    url = this.url,
    imageUrl = this.imageUrl,
    language = this.language,
    publishedAt = this.publishedAt,
    source = this.source,
    locale = this.locale,
    keywords = this.keywords,
    table = table,
)


