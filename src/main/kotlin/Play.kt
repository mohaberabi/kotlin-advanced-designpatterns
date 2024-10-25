package org.example

import kotlinx.coroutines.*


suspend fun main() {

    coroutineScope {
        delay(5000L)
        println("scope 1")
    }
    coroutineScope {
        delay(2000L)
        println("scope 2")
    }
    coroutineScope {
        delay(1000L)
        println("scope 3")
    }

    coroutineScope {
        launch {
            delay(5000L)
            println("launch 1 inner ")

        }
        launch {
            delay(1000L)
            println("launch 3 inner")
        }
        launch {
            delay(2000L)
            println("launch 2 inner ")
        }
    }
}