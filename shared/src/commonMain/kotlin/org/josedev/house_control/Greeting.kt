package org.josedev.house_control

import kotlin.random.Random

class Greeting {
    private val platform = getPlatform()

    fun greet(): List<String> = buildList {
        add(if (Random.nextBoolean()) "Hi" else "Hello")
        add("Guess who is > ${platform.name.reversed()}")
    }
}