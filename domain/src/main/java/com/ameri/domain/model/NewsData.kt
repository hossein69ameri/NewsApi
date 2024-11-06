package com.ameri.domain.model


data class NewsData(
    var `data`: List<Data>? = null
) {
    data class Data(
        var id: Int? = null,
        var uuid: String? = null,
        var title: String? = null,
        var description: String? = null,
        var snippet: String? = null,
        var url: String? = null,
        var imageUrl: String? = null,
        var language: String? = null,
        var publishedAt: String? = null,
        var source: String? = null,
        var keywords: String? = null,
        var categories: List<String?>? = null,
        var relevanceScore: Any? = null,
        var locale: String? = null,
        var table: String? = null
    )
}