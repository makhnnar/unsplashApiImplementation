package com.pedrogomez.develepersfinder.di

import androidx.room.Room
import com.pedrogomez.develepersfinder.repository.HitsProvider
import com.pedrogomez.develepersfinder.repository.local.HitsDataBase
import com.pedrogomez.develepersfinder.repository.local.HitsLocalRepo
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
                androidApplication(),
                HitsDataBase::class.java,
                "Hits.db"
        ).fallbackToDestructiveMigration()
        .build()
    }
}

val dbProvider = module{
    single<HitsProvider.LocalDataSource>{
        HitsLocalRepo(
                get<HitsDataBase>().hits()
        )
    }
}