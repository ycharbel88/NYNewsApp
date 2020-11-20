package com.chy.nynewsapp.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Media(
    @Json(name = "approved_for_syndication")
    var approvedForSyndication: Int,
    @Json(name = "caption")
    var caption: String,
    @Json(name = "copyright")
    var copyright: String,
    @Json(name = "media-metadata")
    var mediaMetadata: List<MediaMetadata>,
    @Json(name = "subtype")
    var subtype: String,
    @Json(name = "type")
    var type: String
): Serializable