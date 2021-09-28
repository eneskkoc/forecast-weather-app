package com.example.mvmm.data.model.geo

import com.google.gson.annotations.SerializedName

data class LocalNames(
    val ascii: String?,
    val ca: String?,
    @SerializedName("en")
    val en: String?,
    val feature_name: String?
)