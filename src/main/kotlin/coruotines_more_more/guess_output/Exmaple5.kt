package org.example.coruotines_more_more.guess_output


import kotlinx.coroutines.*

fun main() = runBlocking {
    val job1 = launch {
        delay(1000L)
        println("Coroutine 1")
    }

    val job2 = launch {
        delay(500L)
        println("Coroutine 2")

    }

    val job3 = launch {
        delay(1000L)
        println("Coroutine 3")

    }
    val job4 = launch {
        delay(593L)
        println("Coroutine 4")

    }
    val job5 = launch {
        delay(593L)
        println("Coroutine 5")
    }
    joinAll(
        job1, job2, job3, job4, job5,
    )

}
