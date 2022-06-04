package com.wazz9p.data.database.menu.localDataSourceImpl

import com.wazz9p.data.database.menu.MenuLocalDataSource
import com.wazz9p.data.database.menu.dao.MenuDao
import com.wazz9p.data.mappers.menu.MenuEntityMapper
import com.wazz9p.domain.model.menu.Dish
import javax.inject.Inject

class MenuRoomDataSource @Inject constructor(
    private val menuDao: MenuDao,
    private val mapper: MenuEntityMapper
) : MenuLocalDataSource {

    override suspend fun saveMenu(data: List<Dish>) {
        data.map { mapper.mapToData(data = it) }
            .let { menuDao.saveMenu(data = it) }
    }

    override suspend fun getMenu(categoryId: Int): List<Dish> {
        val data = menuDao.getMenu(categoryId)
        return data.map { mapper.mapToDomain(data = it) }
    }

    override suspend fun getDish(dishId: Int): Dish {
        val data = menuDao.getDish(dishId)
        return mapper.mapToDomain(data)
    }
}