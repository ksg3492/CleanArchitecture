package com.sunggil.cleanarchitecture.di

import com.sunggil.cleanarchitecture.data.repository.VersionRemoteDataSource
import com.sunggil.cleanarchitecture.data.repository.VersionRepositoryImpl
import com.sunggil.cleanarchitecture.domain.repository.VersionRepository
import com.sunggil.cleanarchitecture.domain.usecase.GetVersionUseCase
import com.sunggil.cleanarchitecture.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val useCaseModule = module {
    single { GetVersionUseCase(get()) }
}

val repoModule = module {
    single<VersionRepository> { VersionRepositoryImpl(get()) }
}

val dataSourceModule = module {
    single { VersionRemoteDataSource(get()) }
}