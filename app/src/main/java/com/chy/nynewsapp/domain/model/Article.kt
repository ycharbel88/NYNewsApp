package com.chy.nynewsapp.domain.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Article(
    @Json(name = "abstract")
    var `abstract`: String,
    @Json(name = "adx_keywords")
    var adxKeywords: String,
    @Json(name = "asset_id")
    var assetId: Long,
    @Json(name = "byline")
    var byline: String,
    @Json(name = "des_facet")
    var desFacet: List<String>,
    @Json(name = "eta_id")
    var etaId: Int,
    @Json(name = "geo_facet")
    var geoFacet: List<String>,
    @Json(name = "id")
    var id: Long,
    @Json(name = "media")
    var media: List<Media>,
    @Json(name = "nytdsection")
    var nytdsection: String,
    @Json(name = "org_facet")
    var orgFacet: List<String>,
    @Json(name = "per_facet")
    var perFacet: List<String>,
    @Json(name = "published_date")
    var publishedDate: String,
    @Json(name = "section")
    var section: String,
    @Json(name = "source")
    var source: String,
    @Json(name = "subsection")
    var subsection: String,
    @Json(name = "title")
    var title: String,
    @Json(name = "type")
    var type: String,
    @Json(name = "updated")
    var updated: String,
    @Json(name = "uri")
    var uri: String,
    @Json(name = "url")
    var url: String
):Serializable