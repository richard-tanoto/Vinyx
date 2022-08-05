package com.richard.vinyx.core.data.source.local.room

import androidx.room.*
import com.richard.vinyx.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM games")
    fun getAllGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM games where isFavorite = 1")
    fun getFavoriteGames(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGames(games: List<GameEntity>)

    @Update
    fun updateGame(game: GameEntity)

}