package com.chy.nynewsapp.data.source.remote

import com.chy.nynewsapp.domain.model.MostViewedArticleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("mostviewed/all-sections/7.json")
    suspend fun getMostViewedArticle(@Query("api-key") apikey:String): MostViewedArticleResponse
}