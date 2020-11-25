package com.chy.nynewsapp.data.repository

import com.chy.nynewsapp.data.service.ApiService
import com.chy.nynewsapp.domain.model.MostViewedArticleResponse
import com.chy.nynewsapp.domain.repository.ArticleRepository

class ArticleRepositoryImp(private val remoteArticle: RemoteArticleImp) : ArticleRepository {
    // used to get Remote article from server
    // Or in Case we Need to add local Database for offline  Caching we call from this repo

    override suspend fun getMostViewedArticle(): MostViewedArticleResponse {
        return remoteArticle.getArticles()
    }
}