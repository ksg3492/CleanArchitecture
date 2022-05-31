package com.sunggil.cleanarchitecture.data.repository

import com.sunggil.cleanarchitecture.data.network.api.VersionApiService
import com.sunggil.cleanarchitecture.data.network.json.mapper
import com.sunggil.cleanarchitecture.domain.model.Version
import io.reactivex.rxjava3.core.Single

class VersionRemoteDataSource(
    private val versionApiService : VersionApiService
) {
    fun getVersion() : Single<Version> {
        return versionApiService.getVersion().map { it.mapper() }
    }
}