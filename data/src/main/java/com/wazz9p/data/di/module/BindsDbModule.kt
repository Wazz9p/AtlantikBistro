package com.wazz9p.data.di.module

import com.wazz9p.data.database.menu.CategoryLocalDataSource
import com.wazz9p.data.database.menu.CategoryRoomDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface BindsDbModule {

    @Binds
    fun bindsLocalDataSource(roomDataSource: CategoryRoomDataSource): CategoryLocalDataSource
}