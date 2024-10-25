package org.example.coruotines_more_more.guess_output

import kotlinx.coroutines.*

fun main() = runBlocking {
    val scope = CoroutineScope(SupervisorJob())

    val job1 = scope.launch {
        delay(1000L)
        println("Job 1 completed")
    }

    val job2 = scope.launch {
        delay(500L)
        throw Exception("Job 2 failed")
    }
    job2.join()
    job1.join()
    println("Finished")
}

// job 1 completed
// finished