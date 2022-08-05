package com.richard.vinyx.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.richard.vinyx.core.domain.usecase.GameUseCase

class HomeViewModel(gameUseCase: GameUseCase) : ViewModel() {

    val games = gameUseCase.getAllGames().asLiveData()

}