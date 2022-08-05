package com.richard.vinyx.core.data

import com.richard.vinyx.core.data.source.local.LocalDataSource
import com.richard.vinyx.core.data.source.remote.RemoteDataSource
import com.richard.vinyx.core.data.source.remote.network.ApiResponse
import com.richard.vinyx.core.data.source.remote.response.GameResponse
import com.richard.vinyx.core.domain.model.Game
import com.richard.vinyx.core.domain.repository.IGameRepository
import com.richard.vinyx.core.utils.AppExecutors
import com.richard.vinyx.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GameRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGameRepository {

    override fun getAllGames(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameResponse>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getAllGames().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> = remoteDataSource.getGames()

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGames(gameList)
            }

        }.asFlow()

    override fun getFavoriteGames(): Flow<List<Game>> {
        return localDataSource.getFavoriteGames().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteGame(game: Game, state: Boolean) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute { localDataSource.setFavoriteGame(gameEntity, state)}
    }

}