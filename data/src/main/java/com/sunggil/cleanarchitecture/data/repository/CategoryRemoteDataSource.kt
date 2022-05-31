package com.sunggil.cleanarchitecture.data.repository

import com.sunggil.cleanarchitecture.data.network.NetworkUtil
import com.sunggil.cleanarchitecture.data.network.api.CategoryApiService
import com.sunggil.cleanarchitecture.data.network.json.CategoryRequestBody
import com.sunggil.cleanarchitecture.data.network.json.mapper
import com.sunggil.cleanarchitecture.domain.model.Category
import io.reactivex.rxjava3.core.Single

class CategoryRemoteDataSource(
    private val categoryApiService : CategoryApiService
) {

    fun getCategory() : Single<ArrayList<Category>> {
        val requestBody = NetworkUtil.makeRequestBody(
            CategoryRequestBody(langCd = "ko_KR")
        )

        return this.categoryApiService.getCategory(requestBody).map { it.mapper() }
    }
}