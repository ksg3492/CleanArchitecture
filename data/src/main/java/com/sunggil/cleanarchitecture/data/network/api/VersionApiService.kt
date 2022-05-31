package com.sunggil.cleanarchitecture.data.network.api

import com.sunggil.cleanarchitecture.data.network.json.VersionResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface VersionApiService {
    @GET("api/version/v1")
    fun getVersion(): Single<VersionResponse>
}