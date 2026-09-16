package org.josedev.house_control.utils

class JvmConsoleLogger : AppLogger {
    override fun logging(tag: String, msg: String) {
        println("[$tag]: $msg")
    }
}