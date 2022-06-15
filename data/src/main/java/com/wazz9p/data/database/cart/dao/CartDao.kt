package com.wazz9p.data.database.cart.dao

import androidx.room.*
import com.wazz9p.data.database.cart.entity.CartEntity

@Dao
interface CartDao {
    @Query("SELECT * FROM cart")
    suspend fun getCart(): List<CartEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDish(dish: CartEntity)

    @Query("DELETE FROM cart")
    suspend fun deleteCart()
}