package org.josedev.house_control.utils

import android.util.Log

class AndroidLogger : AppLogger {
    override fun logging(tag: String, msg: String) {
        Log.d(tag, msg)
    }
}