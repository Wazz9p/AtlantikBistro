package com.wazz9p.data.database.menu.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wazz9p.data.database.menu.entities.MenuEntity

@Dao
interface MenuDao {
    @Query("SELECT * FROM dishes WHERE categoryId = :categoryId")
    suspend fun getMenu(categoryId: Int): List<MenuEntity>

    @Query("SELECT * FROM dishes Where id = :dishId")
    suspend fun getDish(dishId: Int): MenuEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMenu(data: List<MenuEntity>)
}