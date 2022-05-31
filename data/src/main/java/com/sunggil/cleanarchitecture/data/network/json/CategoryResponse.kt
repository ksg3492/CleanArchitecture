package com.sunggil.cleanarchitecture.data.network.json

import com.google.gson.annotations.SerializedName
import com.sunggil.cleanarchitecture.domain.model.Category

data class CategoryResponse(
    @SerializedName("categId")
    var categId: Long = 0L,
    @SerializedName("langCd")
    var langCd: String = "",
    @SerializedName("categText")
    var categText: String = "",
    @SerializedName("categTextLf")
    var categTextLf: String = "",
    @SerializedName("categImg")
    var categImg: String = "",
    @SerializedName("dispNo")
    var dispNo: Int = 0,
)

fun ArrayList<CategoryResponse>.mapper() : ArrayList<Category> {
    return this.mapIndexed { index, response ->
        Category(
            uiIndex = index,
            categId = response.categId,
            categText = response.categText,
            categTextLf = response.categTextLf,
            categImg = response.categImg,
            dispNo = response.dispNo,
            maxCount = 0,
            isSelected = false,
        )
    } as ArrayList<Category>
}