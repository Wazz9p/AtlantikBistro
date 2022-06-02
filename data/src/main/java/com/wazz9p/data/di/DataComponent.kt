package com.wazz9p.data.di

import com.wazz9p.data.di.module.BindsRepositoryModule
import com.wazz9p.data.di.module.DatabaseModule
import com.wazz9p.data.di.module.NetworkModule
import dagger.Component

@Component(modules = [NetworkModule::class, BindsRepositoryModule::class, DatabaseModule::class])
@DataScope
interface DataComponent {

    @Component.Builder
    interface Builder {
        fun build(): DataComponent
    }
}