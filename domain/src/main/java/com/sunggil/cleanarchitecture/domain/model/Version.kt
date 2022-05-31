package com.sunggil.cleanarchitecture.domain.model

data class Version(
    var osCode : String = "",
    var lastVer : Int = 0,
    var lastVerNm : String = "",
    var minVer : Int = 0,
    var minVerNm : String = "",
    var forceUpdate : Boolean = false
)