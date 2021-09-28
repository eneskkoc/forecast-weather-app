package com.example.mvmm.data.model.forecast

import com.google.gson.annotations.SerializedName

data class Temp(
    val day: Double?,
    val eve: Double?,
    @SerializedName("max")
    val max: Double?,
    val min: Double?,
    val morn: Double?,
    val night: Double?
)