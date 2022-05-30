package com.sunggil.cleanarchitecture.di

import com.sunggil.cleanarchitecture.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
}