package com.safened.sdk

/**
 * Simple logger.
 *
 * Created on 20-6-2019 at 21:36.
 */
@Deprecated("Use another logger, this one is just a simple logger.")
class Logger(var enabled: Boolean = true) {

    fun enter(name: String) {
        if (enabled) {
            println("-------------------- $name --------------------")
        }
    }

    fun info(message: String) {
        if (enabled) {
            println(message)
        }
    }

}