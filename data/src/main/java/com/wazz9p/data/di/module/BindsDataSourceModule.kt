package com.wazz9p.data.di.module

import com.wazz9p.data.database.menu.CategoryLocalDataSource
import com.wazz9p.data.database.menu.MenuLocalDataSource
import com.wazz9p.data.database.menu.localDataSourceImpl.CategoryRoomDataSource
import com.wazz9p.data.database.menu.localDataSourceImpl.MenuRoomDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface BindsDataSourceModule {

    @Binds
    fun bindsCategoryLocalDataSource(roomDataSource: CategoryRoomDataSource): CategoryLocalDataSource

    @Binds
    fun bindsMenuLocalDataSource(roomDataSource: MenuRoomDataSource): MenuLocalDataSource
}