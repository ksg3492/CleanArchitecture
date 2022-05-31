package com.sunggil.cleanarchitecture.data.network.api

import com.sunggil.cleanarchitecture.data.network.json.CategoryResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface CategoryApiService {
    @GET("api/category/list/v1")
    fun getCategory(): Single<ArrayList<CategoryResponse>>
}