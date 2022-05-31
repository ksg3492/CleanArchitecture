package com.sunggil.cleanarchitecture.data.repository

import com.sunggil.cleanarchitecture.data.network.api.CategoryApiService
import com.sunggil.cleanarchitecture.data.network.json.mapper
import com.sunggil.cleanarchitecture.domain.model.Category
import io.reactivex.rxjava3.core.Single

class CategoryRemoteDataSource(
    private val categoryApiService : CategoryApiService
) {

    fun getCategory() : Single<ArrayList<Category>> {
        return this.categoryApiService.getCategory().map { it.mapper() }
    }
}