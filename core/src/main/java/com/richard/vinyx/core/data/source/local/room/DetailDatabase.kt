package com.richard.vinyx.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.richard.vinyx.core.data.source.local.entity.DetailEntity
import com.richard.vinyx.core.utils.Converters

@Database(entities = [DetailEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DetailDatabase : RoomDatabase() {
    abstract fun detailDao(): DetailDao
}