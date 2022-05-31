package com.sunggil.cleanarchitecture.domain.repository

import com.sunggil.cleanarchitecture.domain.model.Version
import io.reactivex.rxjava3.core.Single

interface VersionRepository {
    fun getVersion() : Single<Version>
}