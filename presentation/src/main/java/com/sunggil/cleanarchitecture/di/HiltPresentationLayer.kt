package com.sunggil.cleanarchitecture.di

import android.util.Log
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
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltPresentationLayer {
    /**
     * UseCase
     */
    @Provides
    fun providesGetVersionUseCase(repository : VersionRepository) : GetVersionUseCase {
        Log.e("SG2","providesGetVersionUseCase()")
        return GetVersionUseCase(repository)
    }

    @Provides
    fun providesGetCategoryUseCase(repository : CategoryRepository) : GetCategoryUseCase {
        Log.e("SG2","providesGetCategoryUseCase()")
        return GetCategoryUseCase(repository)
    }

    /**
     * Repository
     */
    @Singleton
    @Provides
    fun providesVersionRepository(dataSource : VersionRemoteDataSource) : VersionRepository {
        Log.e("SG2","providesVersionRepository()")
        return VersionRepositoryImpl(dataSource)
    }

    @Singleton
    @Provides
    fun providesCategoryRepository(dataSource : CategoryRemoteDataSource) : CategoryRepository {
        Log.e("SG2","providesCategoryRepository()")
        return CategoryRepositoryImpl(dataSource)
    }

    //todo Remote Data Source 까지 DI 로 해야할까? Repository 에서 멤버로 쓰면 될거같은데..
    /**
     * Remote Data Source
     */
    @Provides
    fun providesVersionRemoteDataSource(apiService : VersionApiService) : VersionRemoteDataSource {
        Log.e("SG2","providesVersionRemoteDataSource()")
        return VersionRemoteDataSource(apiService)
    }

    @Provides
    fun providesCategoryRemoteDataSource(apiService : CategoryApiService) : CategoryRemoteDataSource {
        Log.e("SG2","providesCategoryRemoteDataSource()")
        return CategoryRemoteDataSource(apiService)
    }

    //todo Retofit Api DI 를 여기서(Presentation Layer) 하는게 맞을까? Data Layer 로 이동하면 깔끔할것 같은데..
    /**
     * Retrofit Api Service
     */
    @Singleton
    @Provides
    fun providesVersionApiService(retrofit : Retrofit) : VersionApiService {
        Log.e("SG2","providesVersionApiService()")
        return retrofit.create(VersionApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesCategoryApiService(retrofit : Retrofit) : CategoryApiService {
        Log.e("SG2","providesCategoryApiService()")
        return retrofit.create(CategoryApiService::class.java)
    }
}