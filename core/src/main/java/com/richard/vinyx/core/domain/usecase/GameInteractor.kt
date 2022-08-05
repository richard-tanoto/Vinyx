package com.richard.vinyx.core.domain.usecase

import com.richard.vinyx.core.data.Resource
import com.richard.vinyx.core.domain.model.Game
import com.richard.vinyx.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: IGameRepository) : GameUseCase {
    override fun getAllGames(): Flow<Resource<List<Game>>> = gameRepository.getAllGames()

    override fun getFavoriteGames(): Flow<List<Game>> = gameRepository.getFavoriteGames()

    override fun setFavoriteGame(game: Game, state: Boolean) =
        gameRepository.setFavoriteGame(game, state)

}