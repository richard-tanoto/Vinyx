package com.richard.vinyx.core.data

import com.richard.vinyx.core.data.source.local.LocalDataSource
import com.richard.vinyx.core.data.source.remote.RemoteDataSource
import com.richard.vinyx.core.data.source.remote.network.ApiResponse
import com.richard.vinyx.core.data.source.remote.response.DetailResponse
import com.richard.vinyx.core.domain.model.Detail
import com.richard.vinyx.core.domain.repository.IDetailRepository
import com.richard.vinyx.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DetailRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IDetailRepository {
    override fun getGameDetail(id: Int): Flow<Resource<Detail?>> =
        object : NetworkBoundResource<Detail?, DetailResponse>() {
            override fun loadFromDB(): Flow<Detail?> {
                return localDataSource.getGameDetail(id).map {
                    DataMapper.mapDetailEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: Detail?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<DetailResponse>> = remoteDataSource.getGameDetail(id)

            override suspend fun saveCallResult(data: DetailResponse) {
                val detailEntity = DataMapper.mapDetailResponseToEntity(data)
                localDataSource.insertGameDetail(detailEntity)
            }

        }.asFlow()

}