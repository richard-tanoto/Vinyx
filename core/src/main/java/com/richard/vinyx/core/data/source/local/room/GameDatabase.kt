package com.richard.vinyx.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.richard.vinyx.core.data.source.local.entity.GameEntity
import com.richard.vinyx.core.utils.Converters

@Database(entities = [GameEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameDatabase: RoomDatabase() {

    abstract fun gameDao(): GameDao

}