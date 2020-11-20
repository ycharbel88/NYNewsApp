package com.chy.nynewsapp.domain.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class MediaMetadata(
    @Json(name = "format")
    var format: String,
    @Json(name = "height")
    var height: Int,
    @Json(name = "url")
    var url: String,
    @Json(name = "width")
    var width: Int
): Serializable