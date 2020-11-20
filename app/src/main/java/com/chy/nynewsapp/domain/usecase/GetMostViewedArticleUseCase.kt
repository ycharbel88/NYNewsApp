package com.chy.nynewsapp.domain.usecase

import com.chy.nynewsapp.domain.exception.ApiErrorHandle
import com.chy.nynewsapp.domain.model.MostViewedArticleResponse
import com.chy.nynewsapp.domain.repository.ArticleRepository
import com.chy.nynewsapp.domain.usecase.base.UseCase

class GetMostViewedArticleUseCase constructor(
    private val articleRepository: ArticleRepository,
    apiErrorHandle: ApiErrorHandle
) : UseCase<MostViewedArticleResponse, GetMostViewedArticleUseCase.Params?>(apiErrorHandle) {

    override suspend fun run(params: Params?): MostViewedArticleResponse {
        return params?.apiKey?.let { articleRepository.getMostViewedArticle(apiKey = it) }!!
    }
    data class Params(val apiKey: String)
}