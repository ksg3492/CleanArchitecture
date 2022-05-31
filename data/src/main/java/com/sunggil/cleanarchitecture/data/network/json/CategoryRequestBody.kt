package com.sunggil.cleanarchitecture.data.network.json

import com.google.gson.annotations.SerializedName

data class CategoryRequestBody(
    @SerializedName("langCd")
    var langCd: String
)
