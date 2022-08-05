package com.richard.vinyx.core.domain.repository

import com.richard.vinyx.core.data.Resource
import com.richard.vinyx.core.domain.model.Game
import kotlinx.coroutines.flow.Flow


interface IGameRepository {
    fun getAllGames(): Flow<Resource<List<Game>>>

    fun getFavoriteGames(): Flow<List<Game>>

    fun setFavoriteGame(game: Game, state: Boolean)

}