package com.example.mvmm.ui.main.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvmm.R
import com.example.mvmm.data.database.entity.DailyEntity
import com.example.mvmm.databinding.ForecastLayoutBinding

class RecyclerForecastAdapter(private val forecast: List<DailyEntity?>) :RecyclerView.Adapter<RecyclerForecastAdapter.ForecastHolder>() {


    override fun getItemCount() = forecast.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ForecastHolder(
                    DataBindingUtil.inflate
                    (
                            LayoutInflater.from(parent.context),
                            R.layout.forecast_layout,
                            parent,
                            false
                    )
            )
    override fun onBindViewHolder(holder: ForecastHolder, position: Int) {

        holder.recyclerviewForecastBinding.list= forecast[position]
        holder.recyclerviewForecastBinding.temp=forecast[position]?.temp
        //holder.recyclerviewForecastBinding.weather= forecast[position]?.weather?.get(position)

    }
    inner class ForecastHolder(val recyclerviewForecastBinding: ForecastLayoutBinding) :
            RecyclerView.ViewHolder(recyclerviewForecastBinding.root)

}
