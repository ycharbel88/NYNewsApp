package com.chy.nynewsapp.domain.repository

import com.chy.nynewsapp.domain.model.MostViewedArticleResponse

interface ArticleRepository{
    // add multiple request
    suspend fun getMostViewedArticle():MostViewedArticleResponse
}