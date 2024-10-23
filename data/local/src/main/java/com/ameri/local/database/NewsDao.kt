package com.ameri.local.database

import androidx.room.Dao
import androidx.room.Query
import com.ameri.local.utils.NEWS_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM $NEWS_TABLE")
    fun loadNews(): Flow<List<NewsEntity>>
}