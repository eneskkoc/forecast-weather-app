package com.example.mvmm.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvmm.enum.BottomNavigationPage.*
import com.example.mvmm.extensions.execute
import javax.inject.Inject


class MainViewModel@Inject constructor() : ViewModel() {
    private val _homeItemClickedLD = MutableLiveData<Unit>()
    val homeItemClickedLD: LiveData<Unit> get() = _homeItemClickedLD

    private val _eventsItemClickedLD = MutableLiveData<Unit>()
    val eventsItemClickedLD: LiveData<Unit> get() = _eventsItemClickedLD

    /////////////////////////////////////////////////////////
    ///////////////////////// Calls /////////////////////////
    /////////////////////////////////////////////////////////

    fun onBottomItemChanged(position: Int) {
        when (values()[position]) {
            TAB_DAILY -> _homeItemClickedLD.execute()
            TAB_WEEK -> _eventsItemClickedLD.execute()

        }
    }
}