
package com.example.mvmm.di

import android.app.Application
import com.example.mvmm.WeatherApp
import com.example.mvmm.data.database.DBModule
import com.example.mvmm.data.service.ApiService
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@PerApplication
@Component(
    modules = [ AndroidSupportInjectionModule::class,
        Bindings::class,
        ApiService::class,
        DBModule::class,
        ViewModelModule::class]
)
interface AppComponent : AndroidInjector<WeatherApp> {
    fun inject(application: Application)
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(application: Application): Builder
        fun build(): AppComponent
    }
}



