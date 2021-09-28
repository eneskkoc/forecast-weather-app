package com.example.mvmm.ui.main.provide

import com.example.mvmm.ui.main.fragments.HomeFragment
import com.example.mvmm.ui.main.fragments.WeeklyFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityProvides {
    @ContributesAndroidInjector
    abstract fun bindHomeFragment():HomeFragment
    @ContributesAndroidInjector
    abstract fun bindWeeklyFragment():WeeklyFragment
}