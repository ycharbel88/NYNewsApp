package com.chy.nynewsapp

import android.app.Application
import com.chy.nynewsapp.di.module.ArticleModule
import com.chy.nynewsapp.di.module.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class NYNewsApplication : Application() {

    private val TAG = NYNewsApplication::class.java.simpleName

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NYNewsApplication)
            androidLogger()
            modules(listOf(ArticleModule, NetworkModule))
        }

    }
}