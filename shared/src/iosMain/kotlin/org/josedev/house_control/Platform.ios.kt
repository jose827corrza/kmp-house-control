package org.josedev.house_control

import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val test: Boolean get() = false
}

actual fun getPlatform(): Platform = IOSPlatform()