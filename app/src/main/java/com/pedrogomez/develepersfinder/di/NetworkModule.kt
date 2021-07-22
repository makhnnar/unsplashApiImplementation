package com.pedrogomez.develepersfinder.di

import com.pedrogomez.develepersfinder.R
import com.pedrogomez.develepersfinder.repository.DataBaseManager
import com.pedrogomez.develepersfinder.repository.remote.ApiRepository
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val networkModule = module {
    single { okHttpKtor }
    single<DataBaseManager.RemoteDataSource> {
        ApiRepository(
            get(),
            androidApplication().getString(R.string.url_api)
        )
    }
}

private val okHttpKtor = HttpClient(CIO) {
    install(JsonFeature) {
        serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
}