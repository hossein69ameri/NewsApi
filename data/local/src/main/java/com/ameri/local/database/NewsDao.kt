package com.ameri.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.ameri.local.utils.NEWS_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveNews(entity: NewsEntity)

    @Query("SELECT * FROM $NEWS_TABLE WHERE `table` = :table")
    fun loadNewsByCategory(table: String): Flow<List<NewsEntity>>

    @Query("DELETE FROM $NEWS_TABLE WHERE `table` = :table")
    suspend fun deleteNewsByCategory(table: String)

}