package com.chy.nynewsapp.data.service

import com.chy.nynewsapp.domain.model.MostViewedArticleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("mostviewed/all-sections/7.json")
    suspend fun getMostViewedArticle(): MostViewedArticleResponse
}