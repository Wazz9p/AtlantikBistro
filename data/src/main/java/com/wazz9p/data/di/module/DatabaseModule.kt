package com.wazz9p.data.di.module

import android.content.Context
import androidx.room.Room
import com.wazz9p.data.database.AppDatabase
import com.wazz9p.data.mappers.menu.CategoryEntityMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Context) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "bistro_db"
    )

    @Singleton
    @Provides
    fun provideCategoryDao(db: AppDatabase) = db.categories()

    @Provides
    fun provideCategoryEntityMapper(): CategoryEntityMapper = CategoryEntityMapper()
}