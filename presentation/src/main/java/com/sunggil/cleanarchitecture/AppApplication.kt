package com.sunggil.cleanarchitecture

import android.app.Application
import com.sunggil.cleanarchitecture.data.di.*
import com.sunggil.cleanarchitecture.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Koin Android logger
            androidLogger()
            //inject Android context
            androidContext(this@AppApplication)
            // use modules
            val listModule = arrayListOf<Module>()
            listModule.add(viewModelModule)
            listModule.add(networkModule)
            listModule.add(networkServiceModule)
            listModule.add(useCaseModule)
            listModule.add(repoModule)
            listModule.add(dataSourceModule)

            modules(listModule)
        }

    }
}