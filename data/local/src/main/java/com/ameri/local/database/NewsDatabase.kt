package com.ameri.local.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NewsEntity::class], version = 17, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun dao(): NewsDao
}