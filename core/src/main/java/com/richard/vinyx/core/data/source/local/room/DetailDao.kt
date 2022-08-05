package com.richard.vinyx.core.data.source.local.room

import androidx.room.*
import com.richard.vinyx.core.data.source.local.entity.DetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DetailDao {
    @Query("SELECT * FROM detail WHERE id = :id")
    fun getGameDetail(id : Int): Flow<DetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGameDetail(detailEntity: DetailEntity)
}