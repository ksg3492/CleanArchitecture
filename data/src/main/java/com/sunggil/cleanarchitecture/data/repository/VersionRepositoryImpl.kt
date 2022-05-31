package com.sunggil.cleanarchitecture.data.repository

import com.sunggil.cleanarchitecture.domain.model.Version
import com.sunggil.cleanarchitecture.domain.repository.VersionRepository
import io.reactivex.rxjava3.core.Single

class VersionRepositoryImpl(
    private val remoteDataSource : VersionRemoteDataSource
) : VersionRepository {
    override fun getVersion() : Single<Version> {
        return remoteDataSource.getVersion()
    }
}