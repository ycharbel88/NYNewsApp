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
import com.chy.nynewsapp.data.repository.RemoteArticleImp
import com.chy.nynewsapp.data.service.ApiService
import com.chy.nynewsapp.domain.exception.ApiErrorHandle
import com.chy.nynewsapp.domain.repository.ArticleRepository
import com.chy.nynewsapp.domain.usecase.GetMostViewedArticleUseCase
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


val NetworkModule = module {
    single() { RemoteArticleImp(get()) }

    single { createService(get()) }

    single { createRetrofit(get(), BuildConfig.URL_SERVER) }

    single { createOkHttpClient() }

    single { createMoshiConverterFactory() }

    single { createMoshi() }
}

class BasicAuthInterceptor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newUrl =
            request.url.newBuilder().addQueryParameter("api-key", BuildConfig.API_KEY).build()
        val newRequest = request.newBuilder().url(newUrl).build()
        return chain.proceed(newRequest)
    }

}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(BasicAuthInterceptor())
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
    return ArticleRepositoryImp( remoteArticle = RemoteArticleImp(apiService))
}

fun createGetArticleUseCase(
    articleRepository: ArticleRepository,
    apiErrorHandle: ApiErrorHandle
): GetMostViewedArticleUseCase {
    return GetMostViewedArticleUseCase(articleRepository, apiErrorHandle)
}
