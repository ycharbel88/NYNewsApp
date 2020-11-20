package com.chy.nynewsapp.domain.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class MostViewedArticleResponse(
    @Json(name = "copyright")
    var copyright: String,
    @Json(name = "num_results")
    var numResults: Int,
    @Json(name = "results")
    var results: List<Article>,
    @Json(name = "status")
    var status: String
): Serializable