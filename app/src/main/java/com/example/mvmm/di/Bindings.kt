package com.example.mvmm.di

import com.example.mvmm.ui.main.MainActivity
import com.example.mvmm.ui.main.provide.MainActivityProvides
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class Bindings {
    @PerActivity
    @ContributesAndroidInjector(modules =[MainActivityProvides::class] )
    abstract fun bindMainActivity():MainActivity
}