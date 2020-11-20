package com.chy.nynewsapp.di.module
import com.chy.nynewsapp.presentation.articles.ArticleViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ArticleModule = module {

    viewModel { ArticleViewModel(get()) }

    single { createGetArticleUseCase(get(), createApiErrorHandle()) }

    // single instance of ArticleRepository
    single { createArticleRepository(get()) }
}