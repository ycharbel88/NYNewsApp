package com.chy.nynewsapp.presentation.articles

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chy.nynewsapp.domain.model.Article
import com.chy.nynewsapp.domain.model.ErrorModel
import com.chy.nynewsapp.domain.model.MostViewedArticleResponse
import com.chy.nynewsapp.domain.usecase.GetMostViewedArticleUseCase

import com.chy.nynewsapp.domain.usecase.base.UseCaseResponse
import com.chy.nynewsapp.presentation.base.BaseViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

class ArticleViewModel constructor(private val getMostViewedArticleUseCase: GetMostViewedArticleUseCase) :
    BaseViewModel() {

    val mostViewedArticle = MutableLiveData<List<Article>>()
    val showProgressbar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    @ExperimentalCoroutinesApi
    fun getMostViewedArticle(apiKey: String) {
        showProgressbar.value = true
        getMostViewedArticleUseCase.invoke(
            params = GetMostViewedArticleUseCase.Params(apiKey),
            object : UseCaseResponse<MostViewedArticleResponse> {
                override fun onSuccess(result: MostViewedArticleResponse) {
                    Log.i(TAG, "result: $result")
                    detectResultAndDisplay(result)
                    showProgressbar.value = false
                }

                override fun onError(errorModel: ErrorModel?) {
                    messageData.value = errorModel?.message
                    showProgressbar.value = false
                }
            })
    }

    private fun detectResultAndDisplay(result: MostViewedArticleResponse) {
        if (result.results.isNullOrEmpty())
            messageData.value = "No data Found"
        else
            mostViewedArticle.value=result.results
    }

    companion object {
        private val TAG = ArticleViewModel::class.java.name
    }

}