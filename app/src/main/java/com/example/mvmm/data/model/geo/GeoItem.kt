package com.example.mvmm.data.model.geo

import com.google.gson.annotations.SerializedName

data class GeoItem(
    @SerializedName("country")
    val country: String?,
    @SerializedName("lat")
    val lat: Double?,
    @SerializedName("local_names")
    val local_names: LocalNames?,
    @SerializedName("lon")
    val lon: Double?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("state")
    val state: String?
)