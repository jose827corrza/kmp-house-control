package org.josedev.house_control.utils

import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE


class KmpKoinLogger(level: Level = Level.INFO) : Logger(level) {
    override fun display(level: Level, msg: MESSAGE) {
        println("[$level] [Koin]: $msg")
    }
}