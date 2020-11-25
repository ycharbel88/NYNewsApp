package com.chy.nynewsapp.data.repository

import com.chy.nynewsapp.data.service.ApiService
import com.chy.nynewsapp.domain.model.*

class RemoteArticleImp(private val apiService: ApiService) : ArticlesDataSource {

    override suspend fun getArticles(): MostViewedArticleResponse {
        return apiService.getMostViewedArticle()
    }
}