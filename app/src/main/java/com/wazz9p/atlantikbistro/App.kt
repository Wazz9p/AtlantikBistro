package com.wazz9p.atlantikbistro

import android.app.Application
import android.content.Context
import com.wazz9p.atlantikbistro.di.AppComponent
import com.wazz9p.atlantikbistro.di.DaggerAppComponent
import com.wazz9p.data.di.DaggerDataComponent
import com.wazz9p.data.di.DataComponent
import com.wazz9p.domain.di.DaggerDomainComponent
import com.wazz9p.domain.di.DomainComponent

class App : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .application(this)
            .domainComponent(provideDomainComponent())
            .dataComponent(provideDataComponent())
            .build()
    }

    private fun provideDomainComponent(): DomainComponent = DaggerDomainComponent.builder().build()

    private fun provideDataComponent(): DataComponent = DaggerDataComponent.builder().build()
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }