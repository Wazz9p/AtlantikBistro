package com.wazz9p.data.database.menu

import com.wazz9p.data.dataSource.MenuDataSource
import com.wazz9p.domain.model.menu.Dish

interface MenuLocalDataSource : MenuDataSource{

    suspend fun saveMenu(data: List<Dish>)
}