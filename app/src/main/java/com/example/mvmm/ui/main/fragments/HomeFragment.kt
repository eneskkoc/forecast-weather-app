package com.example.mvmm.ui.main.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mvmm.R
import com.example.mvmm.data.database.ConnectivityInterceptor
import com.example.mvmm.databinding.FragmentHomeBinding
import dagger.android.support.AndroidSupportInjection
import java.util.*
import javax.inject.Inject


class HomeFragment : Fragment() {
    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory
    lateinit var viewModel: HomeFragmentViewModel
    private var binding: FragmentHomeBinding? = null
    @Inject
    lateinit var connectivityInterceptor: ConnectivityInterceptor


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this, viewModelProvider).get(HomeFragmentViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding?.geo = viewModel
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCurrent()

            binding?.imSearch?.setOnClickListener {
                if (connectivityInterceptor.isConnectionOn()){
                 if (binding?.etCitySearch?.text?.length ?: 0 > 3) {
                    viewModel.deleteDatabase()
                    // viewModel.coordinate(binding?.etCitySearch?.text.toString())
                    viewModel.getName(binding?.etCitySearch?.text.toString().toLowerCase((Locale("tr", "TR"))))
                 }
            }
                else{
                    Toast.makeText(context,"İnternet Bağlantınızı Kontrol Ediniz",Toast.LENGTH_SHORT).show()
                }
        }

        viewModel.data.observe(viewLifecycleOwner, { state ->
            when (state) {
                is HomeFragmentViewModel.State.OnCompleted -> onCompleted()
                is HomeFragmentViewModel.State.OnError -> onError(state.error)
                is HomeFragmentViewModel.State.OnMessage -> onMessage()

            }
        })
    }

    private fun onMessage() {
        Toast.makeText(context,viewModel.mError, Toast.LENGTH_LONG).show()
    }

    private fun onError(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }


    private fun onCompleted() {
        binding?.tvCity?.text =viewModel.mCoord?.name
        binding?.tvDay?.text=viewModel.mCurrent?.date()
        binding?.tvSicaklik?.text= viewModel.mCurrent?.tempRound().toString()+"°"
        binding?.tvHumidity?.text="Nem:"+viewModel.mCurrent?.humidity
        binding?.tvDescription?.text=viewModel.mWeatherr?.description
    }

}