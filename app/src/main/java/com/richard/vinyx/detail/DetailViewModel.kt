package com.richard.vinyx.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.richard.vinyx.core.data.Resource
import com.richard.vinyx.core.domain.model.Detail
import com.richard.vinyx.core.domain.model.Game
import com.richard.vinyx.core.domain.usecase.DetailUseCase
import com.richard.vinyx.core.domain.usecase.GameUseCase

class DetailViewModel(private val gameUseCase: GameUseCase, private val detailUseCase: DetailUseCase) : ViewModel() {

    fun getGameDetail(id : Int) : LiveData<Resource<Detail?>> = detailUseCase.getGameDetail(id).asLiveData()

    fun setFavoriteGame(game: Game, state: Boolean) = gameUseCase.setFavoriteGame(game, state)

}