package com.wazz9p.atlantikbistro.di

import android.app.Application
import com.wazz9p.atlantikbistro.screens.category.CategoryFragment
import com.wazz9p.data.di.DataComponent
import com.wazz9p.domain.di.DomainComponent
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [DomainComponent::class, DataComponent::class])
@AppScope
interface AppComponent {

    fun inject(fragment: CategoryFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun domainComponent(domainComponent: DomainComponent): AppComponent.Builder

        fun dataComponent(dataComponent: DataComponent): AppComponent.Builder

        fun build(): AppComponent
    }
}