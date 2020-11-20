package com.chy.nynewsapp.di.module

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import com.chy.nynewsapp.BuildConfig
import com.chy.nynewsapp.data.repository.ArticleRepositoryImp
import com.chy.nynewsapp.data.source.remote.ApiService
import com.chy.nynewsapp.domain.exception.ApiErrorHandle
import com.chy.nynewsapp.domain.repository.ArticleRepository
import com.chy.nynewsapp.domain.usecase.GetMostViewedArticleUseCase


val NetworkModule = module {

    single { createService(get()) }

    single { createRetrofit(get(), BuildConfig.URL_SERVER) }

    single { createOkHttpClient() }

    single { createMoshiConverterFactory() }

    single { createMoshi() }
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create()).build()
}


fun createMoshi(): Moshi {
    return Moshi.Builder().build()
}

fun createMoshiConverterFactory(): MoshiConverterFactory {
    return MoshiConverterFactory.create()
}


fun createApiErrorHandle(): ApiErrorHandle {
    return ApiErrorHandle()
}

fun createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

fun createArticleRepository(apiService: ApiService): ArticleRepository {
    return ArticleRepositoryImp(apiService)
}

fun createGetArticleUseCase(
    articleRepository: ArticleRepository,
    apiErrorHandle: ApiErrorHandle
): GetMostViewedArticleUseCase {
    return GetMostViewedArticleUseCase(articleRepository, apiErrorHandle)
}
