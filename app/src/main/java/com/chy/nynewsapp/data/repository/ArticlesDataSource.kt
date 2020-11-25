package com.chy.nynewsapp.data.repository

import com.chy.nynewsapp.domain.model.MostViewedArticleResponse


interface ArticlesDataSource{
    suspend fun getArticles(): MostViewedArticleResponse
}