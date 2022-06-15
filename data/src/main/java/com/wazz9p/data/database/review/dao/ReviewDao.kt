package com.wazz9p.data.database.review.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wazz9p.data.database.review.entity.ReviewEntity

@Dao
interface ReviewDao {
    @Query("SELECT * FROM reviews")
    suspend fun getReviews(): List<ReviewEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveReview(data: List<ReviewEntity>)
}