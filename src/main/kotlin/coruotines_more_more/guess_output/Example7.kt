package org.example.coruotines_more_more.guess_output

import kotlinx.coroutines.*

fun main() = runBlocking {
    val job = launch {
        repeat(5) { i ->
            println("Job is working: $i")
            delay(500L)
        }
    }

    delay(1100L)
    println("Cancelling job...")
    job.cancelAndJoin()
    println("Job cancelled")
}
// job 0 is working
// job1 is working
//canceling job
// job cancled
