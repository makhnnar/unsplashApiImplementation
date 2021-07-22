package com.pedrogomez.develepersfinder.di

import androidx.room.Room
import com.pedrogomez.develepersfinder.repository.DataBaseManager
import com.pedrogomez.develepersfinder.repository.local.DataBase
import com.pedrogomez.develepersfinder.repository.local.DBLocalRepo
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
                androidApplication(),
                DataBase::class.java,
                "Hits.db"
        ).fallbackToDestructiveMigration()
        .build()
    }
}

val dbProvider = module{
    single<DataBaseManager.LocalDataSource>{
        DBLocalRepo(
                get<DataBase>().hits()
        )
    }
}