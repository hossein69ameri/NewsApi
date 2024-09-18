package com.ameri.domain.models


import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("meta")
    var meta: Meta? = null,
    @SerializedName("data")
    var `data`: List<Data>? = null
) {
    data class Meta(
        @SerializedName("found")
        var found: Int? = null, // 1105215
        @SerializedName("returned")
        var returned: Int? = null, // 3
        @SerializedName("limit")
        var limit: Int? = null, // 3
        @SerializedName("page")
        var page: Int? = null // 1
    )

    data class Data(
        @SerializedName("uuid")
        var uuid: String? = null, // 15e29a9d-c2cf-4797-ba4b-a73bcabc2178
        @SerializedName("title")
        var title: String? = null, // The Pop-Tarts Bowl and the future of college football's postseason
        @SerializedName("description")
        var description: String? = null, // Amid a tumultuous college sports landscape, the Pop-Tarts Bowl has shown a way forward.
        @SerializedName("keywords")
        var keywords: String? = null,
        @SerializedName("snippet")
        var snippet: String? = null, // Open Extended ReactionsFor Michigan fans, the moment when the Wolverines clinched a national championship in January will echo through generations.For every...
        @SerializedName("url")
        var url: String? = null, // https://www.espn.com/college-football/story/_/id/40710345/pop-tarts-bowl-season-future-college-football
        @SerializedName("image_url")
        var imageUrl: String? = null, // https://a.espncdn.com/combiner/i?img=%2Fphoto%2F2024%2F0805%2Fncf_pop%2Dtart%2Dbowl%2Dfuture%2Dcfb_16x9.jpg
        @SerializedName("language")
        var language: String? = null, // en
        @SerializedName("published_at")
        var publishedAt: String? = null, // 2024-08-06T12:41:28.000000Z
        @SerializedName("source")
        var source: String? = null, // espn.com
        @SerializedName("categories")
        var categories: List<String?>? = null,
        @SerializedName("relevance_score")
        var relevanceScore: Any? = null, // null
        @SerializedName("locale")
        var locale: String? = null // us
    )
}