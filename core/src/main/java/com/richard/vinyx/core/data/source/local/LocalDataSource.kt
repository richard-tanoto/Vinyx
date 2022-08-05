package com.richard.vinyx.core.data.source.local

import com.richard.vinyx.core.data.source.local.entity.DetailEntity
import com.richard.vinyx.core.data.source.local.entity.GameEntity
import com.richard.vinyx.core.data.source.local.room.DetailDao
import com.richard.vinyx.core.data.source.local.room.GameDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val gameDao: GameDao, private val detailDao: DetailDao) {

    fun getAllGames(): Flow<List<GameEntity>> = gameDao.getAllGames()

    fun getFavoriteGames(): Flow<List<GameEntity>> = gameDao.getFavoriteGames()

    suspend fun insertGames(games: List<GameEntity>) = gameDao.addGames(games)

    fun setFavoriteGame(game: GameEntity, state: Boolean) {
        game.isFavorite = state
        gameDao.updateGame(game)
    }

    fun getGameDetail(id: Int): Flow<DetailEntity> = detailDao.getGameDetail(id)

    suspend fun insertGameDetail(detailEntity : DetailEntity) = detailDao.addGameDetail(detailEntity)

}