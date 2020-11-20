package com.chy.nynewsapp.data.repository

import com.chy.nynewsapp.di.module.ArticleModule
import com.chy.nynewsapp.di.module.NetworkModule
import org.junit.Test
import org.koin.core.logger.Level
import org.koin.dsl.koinApplication
import org.koin.test.AutoCloseKoinTest
import org.koin.test.check.checkModules

class KoinModuleTest : AutoCloseKoinTest() {

    @Test
    fun testCoreModule() {
        koinApplication {
            printLogger(Level.DEBUG)
            modules(listOf(ArticleModule, NetworkModule))
        }.checkModules()
    }

}