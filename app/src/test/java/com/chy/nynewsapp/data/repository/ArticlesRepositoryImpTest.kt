package com.chy.nynewsapp.data.repository

import com.chy.nynewsapp.domain.model.MostViewedArticleResponse
import com.chy.nynewsapp.utils.Constants
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test


class ArticlesRepositoryImpTest {

    @MockK
    lateinit var articleRepository: ArticleRepositoryImp

    @Before
    fun setUp() {
        MockKAnnotations.init(this) //for initialization
    }

    @Test
    fun getArticlesData() = runBlocking {
        val mostViewedArticleResponse = mockk<MostViewedArticleResponse>()
        every { runBlocking { articleRepository.getMostViewedArticle(Constants.ApiKey) } } returns (mostViewedArticleResponse)

        val result = articleRepository.getMostViewedArticle(Constants.ApiKey)
        MatcherAssert.assertThat(
            "Received result [$result] & mocked [$mostViewedArticleResponse] must be matches on each other!",
            result,
            CoreMatchers.`is`(mostViewedArticleResponse)
        )
    }
}