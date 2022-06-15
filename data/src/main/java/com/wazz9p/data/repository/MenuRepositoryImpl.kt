package com.wazz9p.data.repository

import com.wazz9p.data.database.menu.localDataSourceImpl.MenuRoomDataSource
import com.wazz9p.data.network.menu.MenuRemoteDataSource
import com.wazz9p.domain.model.menu.Dish
import com.wazz9p.domain.repository.menu.MenuRepository
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

class MenuRepositoryImpl @Inject constructor(
    private val localDataSource: MenuRoomDataSource,
    private val remoteDataSource: MenuRemoteDataSource
) : MenuRepository {
    override suspend fun getMenu(categoryId: Int): List<Dish> {
        return try {
            val response = remoteDataSource.getMenu(categoryId)
            localDataSource.saveMenu(response)
            response
        } catch (e: Exception) {
            localDataSource.getMenu(categoryId)
        }
    }

    override suspend fun getDish(dishId: Int): Dish = localDataSource.getDish(dishId)
}