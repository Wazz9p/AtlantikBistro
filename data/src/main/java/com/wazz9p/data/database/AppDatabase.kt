package com.wazz9p.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wazz9p.data.database.menu.dao.CategoryDao
import com.wazz9p.data.database.menu.dao.MenuDao
import com.wazz9p.data.database.menu.entities.CategoryEntity
import com.wazz9p.data.database.menu.entities.MenuEntity
import com.wazz9p.data.database.news.dao.NewsDao
import com.wazz9p.data.database.news.entity.NewsEntity
import com.wazz9p.data.database.review.dao.ReviewDao
import com.wazz9p.data.database.review.entity.ReviewEntity
import com.wazz9p.domain.model.review.Review

@Database(
    entities = [
        CategoryEntity::class,
        MenuEntity::class,
        NewsEntity::class,
        ReviewEntity::class
    ],
    version = 4, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categories(): CategoryDao

    abstract fun menu(): MenuDao

    abstract fun news(): NewsDao

    abstract fun reviews(): ReviewDao
}