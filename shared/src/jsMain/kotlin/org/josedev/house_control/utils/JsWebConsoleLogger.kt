package org.josedev.house_control.utils

class JsWebConsoleLogger : AppLogger {
    override fun logging(tag: String, msg: String) {
        println("[$tag] $msg")
    }
}