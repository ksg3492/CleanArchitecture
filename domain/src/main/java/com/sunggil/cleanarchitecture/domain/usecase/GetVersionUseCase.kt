package com.sunggil.cleanarchitecture.domain.usecase

import com.sunggil.cleanarchitecture.domain.model.Version
import com.sunggil.cleanarchitecture.domain.repository.VersionRepository
import io.reactivex.rxjava3.core.Single

class GetVersionUseCase(
    private val versionRepository : VersionRepository
) {
    fun getVersion() : Single<Version> = this.versionRepository.getVersion()
}