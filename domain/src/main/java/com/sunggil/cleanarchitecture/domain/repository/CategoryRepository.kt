package com.sunggil.cleanarchitecture.domain.repository

import com.sunggil.cleanarchitecture.domain.model.Category
import io.reactivex.rxjava3.core.Single

interface CategoryRepository {
    fun getCategory() : Single<ArrayList<Category>>
}