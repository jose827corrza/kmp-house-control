package org.josedev.house_control.di

import org.josedev.house_control.auth.AndroidTokenStorage
import org.josedev.house_control.auth.TokenStorage
import org.josedev.house_control.utils.AndroidLogger
import org.josedev.house_control.utils.AppLogger
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val loggingModule = module {
    single<TokenStorage> { AndroidTokenStorage(get()) }
    singleOf(::AndroidLogger) bind AppLogger::class
}