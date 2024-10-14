package com.ameri.mapper

import com.ameri.domain.model.NewsData
import com.ameri.remote.models.ResponseData


fun ResponseData.toNews(): NewsData = NewsData(
    data = this.data?.map { it.toNewsDataItem() }
)

fun ResponseData.Data.toNewsDataItem(): NewsData.Data = NewsData.Data(
    uuid = this.uuid,
    title = this.title,
    description = this.description,
    snippet = this.snippet,
    url = this.url,
    imageUrl = this.imageUrl,
    language = this.language,
    publishedAt = this.publishedAt,
    source = this.source,
    categories = this.categories,
    relevanceScore = this.relevanceScore,
    locale = this.locale
)
