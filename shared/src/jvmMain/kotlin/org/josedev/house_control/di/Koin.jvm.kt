package org.josedev.house_control.di

import org.josedev.house_control.auth.JvmTokenStorage
import org.josedev.house_control.auth.TokenStorage
import org.josedev.house_control.utils.AppLogger
import org.josedev.house_control.utils.JvmConsoleLogger
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val loggingModule = module {
    single<TokenStorage> { JvmTokenStorage() }
    singleOf(::JvmConsoleLogger) bind AppLogger::class
}