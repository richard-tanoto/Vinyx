package com.richard.vinyx.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.richard.vinyx.core.domain.usecase.GameUseCase

class FavoriteViewModel(gameUseCase: GameUseCase) : ViewModel() {

    val favoriteGames = gameUseCase.getFavoriteGames().asLiveData()

}