package com.wazz9p.data.database.news.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wazz9p.data.database.news.entity.NewsEntity

interface NewsDao {

    @Query("SELECT * FROM news")
    suspend fun getNews(): List<NewsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNews(data: List<NewsEntity>)
}