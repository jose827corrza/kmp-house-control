package org.josedev.house_control

interface Platform {
    val name: String
    val test: Boolean
}

expect fun getPlatform(): Platform