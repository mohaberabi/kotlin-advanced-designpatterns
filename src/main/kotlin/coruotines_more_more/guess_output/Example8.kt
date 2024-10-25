package org.example.coruotines_more_more.guess_output

import kotlinx.coroutines.*

fun main() = runBlocking {
    val dispatcher = newSingleThreadContext("MyThread")

    launch(dispatcher) {
        println("Running on: ${Thread.currentThread().name}")
        delay(1000L)
        println("Still running on: ${Thread.currentThread().name}")
    }

    println("Main thread: ${Thread.currentThread().name}")
}

// running on
// main thread
// still running on