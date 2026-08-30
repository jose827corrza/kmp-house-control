package org.josedev.house_control

class JVMPlatform : Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
    override val test: Boolean = false
}

actual fun getPlatform(): Platform = JVMPlatform()