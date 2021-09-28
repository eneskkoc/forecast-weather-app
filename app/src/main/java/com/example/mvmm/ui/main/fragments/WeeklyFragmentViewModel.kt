package com.example.mvmm.ui.main.fragments

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvmm.data.AppDataManager
import com.example.mvmm.data.database.entity.DailyEntity
import javax.inject.Inject

class WeeklyFragmentViewModel @Inject constructor(private var appDataManager: AppDataManager) :
    ViewModel() {

    val data = MutableLiveData<State>()

    @SuppressLint("CheckResult")
    fun listDaily() {
        appDataManager.database().subscribe { db ->
            val mData = db.dailyDao().getDaily()
            data.postValue(State.onDaily(mData))
        }
    }
    sealed class State {
        data class onDaily(val daily:List<DailyEntity> ): State()
    }
}