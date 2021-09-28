package com.example.mvmm.ui.main.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvmm.R
import com.example.mvmm.data.database.entity.DailyEntity
import com.example.mvmm.databinding.FragmentWeeklyBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class WeeklyFragment : Fragment() {

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory
    lateinit var viewModel: WeeklyFragmentViewModel
    private var binding: FragmentWeeklyBinding? = null


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this, viewModelProvider).get(WeeklyFragmentViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_weekly, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.listDaily()
        binding?.recyclerForecast?.layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewModel.data.observe(viewLifecycleOwner, { state ->
            when (state) {
                is WeeklyFragmentViewModel.State.onDaily -> onDaily(state.daily)
            }
        })
    }

    private fun onDaily(daily: List<DailyEntity>) {

        binding?.recyclerForecast?.adapter = RecyclerForecastAdapter(daily.sortedBy { it.dt })

    }
}