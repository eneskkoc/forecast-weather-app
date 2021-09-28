package com.example.mvmm.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvmm.ui.main.MainViewModel
import com.example.mvmm.ui.main.fragments.HomeFragmentViewModel
import com.example.mvmm.ui.main.fragments.WeeklyFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @PerApplication
    abstract fun bindViewModelFactory(factory:ViewModelFactory) :ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeFragmentViewModel::class)
    abstract fun bindHomeFragmentViewModel(viewModel:HomeFragmentViewModel):ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeeklyFragmentViewModel::class)
    abstract fun bindWeeklyFragmentViewModel(viewModel:WeeklyFragmentViewModel):ViewModel
}