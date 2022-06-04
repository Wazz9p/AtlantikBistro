package com.wazz9p.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wazz9p.data.database.menu.dao.CategoryDao
import com.wazz9p.data.database.menu.dao.MenuDao
import com.wazz9p.data.database.menu.entities.CategoryEntity
import com.wazz9p.data.database.menu.entities.MenuEntity

@Database(
    entities = [
        CategoryEntity::class,
        MenuEntity::class
    ],
    version = 2, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categories(): CategoryDao

    abstract fun menu(): MenuDao
}