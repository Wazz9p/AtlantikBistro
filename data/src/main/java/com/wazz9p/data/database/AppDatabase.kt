package com.wazz9p.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wazz9p.data.database.menu.dao.CategoryDao
import com.wazz9p.data.database.menu.entities.CategoryEntity

@Database(entities = [CategoryEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categories(): CategoryDao
}