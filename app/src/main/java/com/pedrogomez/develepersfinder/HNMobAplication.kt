package com.pedrogomez.develepersfinder

import android.app.Application
import com.pedrogomez.develepersfinder.di.dbModule
import com.pedrogomez.develepersfinder.di.dbProvider
import com.pedrogomez.develepersfinder.di.networkModule
import com.pedrogomez.develepersfinder.view.di.productsRepository
import com.pedrogomez.develepersfinder.view.di.viewModelListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class HNMobAplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(
                this@HNMobAplication
            )
            androidLogger()
            modules(
                listOf(
                    networkModule,
                    productsRepository,
                    viewModelListModule,
                    dbModule,
                    dbProvider
                )
            )
        }
    }

}