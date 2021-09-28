
package com.example.mvmm


import android.content.Context
import androidx.multidex.MultiDex
import com.example.mvmm.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class WeatherApp: DaggerApplication() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
    override fun applicationInjector(): AndroidInjector<out WeatherApp> {
    return DaggerAppComponent.builder().app(this).build()
    }
}

