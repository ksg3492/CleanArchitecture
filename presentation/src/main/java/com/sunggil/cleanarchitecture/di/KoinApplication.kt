package com.sunggil.cleanarchitecture.di

import com.sunggil.cleanarchitecture.data.network.api.CategoryApiService
import com.sunggil.cleanarchitecture.data.network.api.VersionApiService
import com.sunggil.cleanarchitecture.data.repository.CategoryRemoteDataSource
import com.sunggil.cleanarchitecture.data.repository.CategoryRepositoryImpl
import com.sunggil.cleanarchitecture.data.repository.VersionRemoteDataSource
import com.sunggil.cleanarchitecture.data.repository.VersionRepositoryImpl
import com.sunggil.cleanarchitecture.domain.repository.CategoryRepository
import com.sunggil.cleanarchitecture.domain.repository.VersionRepository
import com.sunggil.cleanarchitecture.domain.usecase.GetCategoryUseCase
import com.sunggil.cleanarchitecture.domain.usecase.GetVersionUseCase
import com.sunggil.cleanarchitecture.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val viewModelModule = module {
    viewModel { MainViewModel(get(), get()) }
}

val networkServiceModule = module {
    single { get<Retrofit>().create(VersionApiService::class.java) }
    single { get<Retrofit>().create(CategoryApiService::class.java) }
}

val useCaseModule = module {
    single { GetVersionUseCase(get()) }
    single { GetCategoryUseCase(get()) }
}

val repoModule = module {
    single<VersionRepository> { VersionRepositoryImpl(get()) }
    single<CategoryRepository> { CategoryRepositoryImpl(get()) }
}

val dataSourceModule = module {
    single { VersionRemoteDataSource(get()) }
    single { CategoryRemoteDataSource(get()) }
}