package com.chy.nynewsapp.data.repository

import com.chy.nynewsapp.data.source.remote.ApiService
import com.chy.nynewsapp.domain.model.MostViewedArticleResponse
import com.chy.nynewsapp.domain.repository.ArticleRepository

class ArticleRepositoryImp(private val apiService: ApiService) : ArticleRepository {
    override suspend fun getMostViewedArticle(apiKey: String): MostViewedArticleResponse {
        return apiService.getMostViewedArticle(apikey = apiKey)
    }
}