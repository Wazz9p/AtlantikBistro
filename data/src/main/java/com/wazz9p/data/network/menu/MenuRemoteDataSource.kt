package com.wazz9p.data.network.menu

import com.wazz9p.data.dataSource.MenuDataSource
import com.wazz9p.data.mappers.menu.MenuResponseMapper
import com.wazz9p.data.network.menu.api.MenuService
import com.wazz9p.domain.model.menu.Dish
import java.net.UnknownHostException
import javax.inject.Inject

class MenuRemoteDataSource @Inject constructor(
    private val menuApi: MenuService,
    private val mapper: MenuResponseMapper
) : MenuDataSource {

    override suspend fun getMenu(categoryId: Int): List<Dish> {
        try {
            val data = menuApi.getMenu(categoryId)
            return data.map { mapper.mapToDomain(data = it) }
        } catch (e: UnknownHostException) {
            throw e
        }
    }


    override suspend fun getDish(dishId: Int): Dish? = null
}