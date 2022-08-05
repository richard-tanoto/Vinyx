package com.richard.vinyx.di

import com.richard.vinyx.core.domain.usecase.DetailInteractor
import com.richard.vinyx.core.domain.usecase.DetailUseCase
import com.richard.vinyx.core.domain.usecase.GameInteractor
import com.richard.vinyx.core.domain.usecase.GameUseCase
import com.richard.vinyx.detail.DetailViewModel
import com.richard.vinyx.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GameUseCase> { GameInteractor(get()) }
    factory<DetailUseCase> { DetailInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get(), get()) }
}