package com.pedrogomez.develepersfinder.view.di

import com.pedrogomez.develepersfinder.repository.HitsProvider
import com.pedrogomez.develepersfinder.view.viewmodel.SharedHitsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productsRepository = module {
    single<SharedHitsViewModel.Repository> {
        HitsProvider(
                get(),
                get()
        )
    }
}

val viewModelListModule = module {
    viewModel {
        SharedHitsViewModel(
            get()
        )
    }
}