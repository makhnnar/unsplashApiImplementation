package com.pedrogomez.develepersfinder.di

import com.pedrogomez.develepersfinder.R
import com.pedrogomez.develepersfinder.repository.HitsProvider
import com.pedrogomez.develepersfinder.repository.remote.HitsApiRepository
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val networkModule = module {
    single { okHttpKtor }
    single<HitsProvider.RemoteDataSource> {
        HitsApiRepository(
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