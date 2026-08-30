package org.josedev.house_control

class WasmPlatform : Platform {
    override val name: String = "Web with Kotlin/Wasm"
    override val test: Boolean = false
}

actual fun getPlatform(): Platform = WasmPlatform()