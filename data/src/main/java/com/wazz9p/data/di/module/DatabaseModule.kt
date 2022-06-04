package com.wazz9p.data.di.module

import android.content.Context
import androidx.room.Room
import com.wazz9p.data.database.AppDatabase
import com.wazz9p.data.mappers.menu.CategoryEntityMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext app: Context): AppDatabase = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "bistro_db"
    ).build()

    @Singleton
    @Provides
    fun provideCategoryDao(db: AppDatabase) = db.categories()

    @Provides
    fun provideCategoryEntityMapper(): CategoryEntityMapper = CategoryEntityMapper()
}