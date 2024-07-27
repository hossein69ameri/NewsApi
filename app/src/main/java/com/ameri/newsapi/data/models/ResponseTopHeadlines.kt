package com.ameri.newsapi.data.models


import com.google.gson.annotations.SerializedName

data class ResponseTopHeadlines(
    @SerializedName("status")
    var status: String? = null, // ok
    @SerializedName("totalResults")
    var totalResults: Int? = null, // 35
    @SerializedName("articles")
    var articles: List<Article>? = null
) {
    data class Article(
        @SerializedName("source")
        var source: Source? = null,
        @SerializedName("author")
        var author: String? = null, // Axios
        @SerializedName("title")
        var title: String? = null, // Kamala Harris gets fundraising boost after Biden drops out of race - Axios
        @SerializedName("description")
        var description: String? = null, // Veteran environmentalist held in Greenland faces extradition to Japan allegedly over anti-whaling activities in the Antarctic years ago, his organization said.
        @SerializedName("url")
        var url: String? = null, // https://news.google.com/rss/articles/CBMiQ2h0dHBzOi8vd3d3LmF4aW9zLmNvbS8yMDI0LzA3LzIyL2thbWFsYS1oYXJyaXMtY2FtcGFpZ24tZnVuZHJhaXNpbmfSAQA?oc=5
        @SerializedName("urlToImage")
        var urlToImage: String? = null, // https://media.cnn.com/api/v1/images/stellar/prod/ap24203669397704.jpg?c=16x9&q=w_800,c_fill
        @SerializedName("publishedAt")
        var publishedAt: String? = null, // 2024-07-22T09:37:02Z
        @SerializedName("content")
        var content: String? = null // Veteran environmentalist Paul Watson was arrested in Greenland on Sunday and faces possible extradition to Japan allegedly over anti-whaling activities in the Antarctic years ago, his organization sa… [+5659 chars]
    ) {
        data class Source(
            @SerializedName("id")
            var id: String? = null, // google-news
            @SerializedName("name")
            var name: String? = null // Google News
        )
    }
}