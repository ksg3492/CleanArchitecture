package com.sunggil.cleanarchitecture

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

//        startKoin {
//            // Koin Android logger
//            androidLogger()
//            //inject Android context
//            androidContext(this@AppApplication)
//            // use modules
//            val listModule = arrayListOf<Module>()
//            listModule.add(viewModelModule)
//            listModule.add(networkModule)
//            listModule.add(networkServiceModule)
//            listModule.add(useCaseModule)
//            listModule.add(repoModule)
//            listModule.add(dataSourceModule)
//
//            modules(listModule)
//        }

    }
}