package com.wazz9p.data.network.menu.api

import com.wazz9p.data.network.menu.response.MenuResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MenuService {
    @GET("/dishes/{id}")
    suspend fun getMenu(@Path("id") categoryId: Int): List<MenuResponse>

    @GET("/dishes")
    suspend fun getAllDishes(): List<MenuResponse>
}