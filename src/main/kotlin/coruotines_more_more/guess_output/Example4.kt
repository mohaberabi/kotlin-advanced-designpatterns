package org.example.coruotines_more_more.guess_output

import kotlinx.coroutines.*

fun main() = runBlocking {
    val deferred = async {
        delay(1000L)
        "Hello from Coroutine"
    }

    println("Result: ${deferred.await()}")
}
//wait 1 second
// result hello from coroutine
