package org.example.coruotines_more_more.guess_output

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job1 = launch {
        delay(1000L)
        println("Coroutine 1")
    }

    val deferred = async {
        delay(500L)
        "Coroutine 2"
    }

    println(deferred.await())
    job1.join()
}
// coroutine 2
// coroutine 1