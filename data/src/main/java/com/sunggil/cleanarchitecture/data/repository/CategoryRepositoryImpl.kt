package com.sunggil.cleanarchitecture.data.repository

import com.sunggil.cleanarchitecture.domain.model.Category
import com.sunggil.cleanarchitecture.domain.repository.CategoryRepository
import io.reactivex.rxjava3.core.Single

class CategoryRepositoryImpl(
    private val categoryRemoteDataSource : CategoryRemoteDataSource
) : CategoryRepository {
    override fun getCategory() : Single<ArrayList<Category>> {
        return this.categoryRemoteDataSource.getCategory()
    }
}