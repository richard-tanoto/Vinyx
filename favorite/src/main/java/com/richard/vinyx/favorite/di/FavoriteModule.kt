package com.richard.vinyx.favorite.di

import com.richard.vinyx.favorite.FavoriteDetailViewModel
import com.richard.vinyx.favorite.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
    viewModel { FavoriteDetailViewModel(get(), get()) }
}