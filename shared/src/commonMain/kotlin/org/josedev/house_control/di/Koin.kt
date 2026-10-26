package org.josedev.house_control.di

import org.josedev.house_control.data.repository.AuthRepositoryImpl
import org.josedev.house_control.data.repository.HouseRepositoryImpl
import org.josedev.house_control.domain.repository.AuthRepository
import org.josedev.house_control.domain.repository.HouseRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val loggingModule: Module

val repositoryModule = module {
    singleOf(::HouseRepositoryImpl) bind HouseRepository::class
    singleOf(::AuthRepositoryImpl) bind AuthRepository::class
}

val appModule = module {
    includes(loggingModule, networkModule, repositoryModule, viewModelModule)
}