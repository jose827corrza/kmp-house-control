package org.josedev.house_control.utils

class JsWebConsoleLogger : AppLogger {
    override fun d(tag: String, msg: String) {
        println("[$tag] $msg")
    }
}