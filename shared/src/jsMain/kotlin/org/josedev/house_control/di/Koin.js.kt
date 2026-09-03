package org.josedev.house_control.di

import org.josedev.house_control.utils.AppLogger
import org.josedev.house_control.utils.JsWebConsoleLogger
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val loggingModule = module {
    singleOf(::JsWebConsoleLogger) bind AppLogger::class
}