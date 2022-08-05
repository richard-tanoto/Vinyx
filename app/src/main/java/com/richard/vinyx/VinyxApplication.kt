package com.richard.vinyx

import com.google.android.play.core.splitcompat.SplitCompatApplication
import com.richard.vinyx.core.di.databaseModule
import com.richard.vinyx.core.di.networkModule
import com.richard.vinyx.core.di.repositoryModule
import com.richard.vinyx.di.useCaseModule
import com.richard.vinyx.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class VinyxApplication: SplitCompatApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@VinyxApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}