package com.sunggil.cleanarchitecture.domain.usecase

import com.sunggil.cleanarchitecture.domain.model.Category
import com.sunggil.cleanarchitecture.domain.repository.CategoryRepository
import io.reactivex.rxjava3.core.Single

class GetCategoryUseCase(
    private val categoryRepository : CategoryRepository
) {
    fun getCategory() : Single<ArrayList<Category>> = this.categoryRepository.getCategory()
}