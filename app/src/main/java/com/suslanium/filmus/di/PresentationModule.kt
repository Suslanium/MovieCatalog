package com.suslanium.filmus.di

import com.suslanium.filmus.presentation.viewmodel.LaunchViewModel
import com.suslanium.filmus.presentation.viewmodel.LoginViewModel
import com.suslanium.filmus.presentation.viewmodel.MainViewModel
import com.suslanium.filmus.presentation.viewmodel.RegistrationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun providePresentationModule() = module {

    viewModel {
        LoginViewModel(get(), get(), get())
    }

    viewModel {
        RegistrationViewModel(get(), get(), get(), get(), get(), get())
    }

    viewModel {
        LaunchViewModel(get())
    }

    viewModel {
        MainViewModel(get())
    }

}