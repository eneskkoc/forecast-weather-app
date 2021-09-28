package com.example.mvmm.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mvmm.R
import com.example.mvmm.BR
import com.example.mvmm.databinding.ActivityMainBinding
import com.example.mvmm.extensions.*
import com.example.mvmm.ui.main.fragments.HomeFragment
import com.example.mvmm.ui.main.fragments.WeeklyFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(),HasAndroidInjector {
    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory
    lateinit var viewmodel: MainViewModel
    private var binding: ActivityMainBinding?=null
    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        viewmodel = ViewModelProvider(this,viewModelProvider).get(MainViewModel::class.java)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.bindData(BR.viewmodel, viewmodel)
        initViews()
        initObservers()

    }

    private fun initViews() {
        initBottomNavigation()
    }

    private fun initObservers() {
        viewmodel.homeItemClickedLD.obs(this) { replaceFragment(R.id.fragment_container, HomeFragment(), false) }
        viewmodel.eventsItemClickedLD.obs(this) { replaceFragment(R.id.fragment_container, WeeklyFragment(), false) }
    }

    private fun initBottomNavigation() {
        bottom_navigation_menu.onItemSelected { viewmodel.onBottomItemChanged(it) }
        bottom_navigation_menu.onItemReselected { }
        replaceFragment(R.id.fragment_container, HomeFragment(), false)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentDispatchingAndroidInjector
    }

}