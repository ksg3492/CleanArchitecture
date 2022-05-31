package com.sunggil.cleanarchitecture.data.network.api

import com.sunggil.cleanarchitecture.data.network.json.CategoryResponse
import io.reactivex.rxjava3.core.Single
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.POST

interface CategoryApiService {
    @POST("api/category/list/v1")
    fun getCategory(
        @Body body : RequestBody
    ): Single<ArrayList<CategoryResponse>>
}