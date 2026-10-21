package org.josedev.house_control.di

import org.josedev.house_control.presentation.viewmodels.HouseViewModel
import org.josedev.house_control.presentation.viewmodels.LoginViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HouseViewModel)
    viewModelOf(::LoginViewModel)
}

