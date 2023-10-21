package com.suslanium.filmus

import android.app.Application
import com.suslanium.filmus.di.provideDataModule
import com.suslanium.filmus.di.provideDomainModule
import com.suslanium.filmus.di.provideNetworkModule
import com.suslanium.filmus.di.providePresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FilmusApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FilmusApp)
            modules(
                provideNetworkModule(),
                provideDataModule(),
                provideDomainModule(),
                providePresentationModule()
            )
        }
    }
}