package com.sunggil.cleanarchitecture.data.network.json

import com.google.gson.annotations.SerializedName
import com.sunggil.cleanarchitecture.domain.Util.Companion.toYNBoolean
import com.sunggil.cleanarchitecture.domain.model.Version

data class VersionResponse(
    @SerializedName("osCode")
    var osCode: String,
    @SerializedName("lastVer")
    var lastVer: Long,
    @SerializedName("lastVerNm")
    var lastVerNm: String,
    @SerializedName("minVer")
    var minVer: Long,
    @SerializedName("minVerNm")
    var minVerNm: String,
    @SerializedName("forceUpdate")
    var forceUpdate: String
)

fun VersionResponse.mapper() : Version {
    return Version(
        osCode = this.osCode,
        lastVer = this.lastVer.toInt(),
        lastVerNm = this.lastVerNm,
        minVer = this.minVer.toInt(),
        minVerNm = this.minVerNm,
        forceUpdate = this.forceUpdate.toYNBoolean()
    )
}