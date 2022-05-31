package com.sunggil.cleanarchitecture.domain.model

data class Category(
    var uiIndex : Int = 0,
    var categId : Long = 0L,
    var categText : String = "",
    var categTextLf : String = "",
    var categImg : String = "",
    var dispNo : Int = 0,
    var maxCount : Int = 0,
    var isSelected : Boolean = false,
)