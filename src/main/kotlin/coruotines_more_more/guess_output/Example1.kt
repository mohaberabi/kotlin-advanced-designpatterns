package org.example.coruotines_more_more.guess_output

import kotlinx.coroutines.*
import kotlin.coroutines.*

fun main() = runBlocking {
    println("Main start: ${Thread.currentThread().name}")
    val dispatcher = object : CoroutineDispatcher() {
        override fun dispatch(context: CoroutineContext, block: Runnable) {
            println("Dispatching coroutine: ${Thread.currentThread().name}")
            Thread {
                println("Running in custom dispatcher: ${Thread.currentThread().name}")
                block.run()
            }.start()
        }
    }


    val scope = CoroutineScope(SupervisorJob() + dispatcher)

    val deferred1 = scope.async {
        println("Coroutine 1 start: ${Thread.currentThread().name}")
        delay(1000)
        println("Coroutine 1 end: ${Thread.currentThread().name}")
        1
    }

    val deferred2 = scope.async {
        println("Coroutine 2 start: ${Thread.currentThread().name}")
        delay(500)
        throw CancellationException("Coroutine 2 failed")
    }

    val deferred3 = scope.async {
        println("Coroutine 3 start: ${Thread.currentThread().name}")
        delay(1500)
        println("Coroutine 3 end: ${Thread.currentThread().name}")
        3
    }

    val deferred4 = scope.async {
        println("Coroutine 4 start: ${Thread.currentThread().name}")
        delay(2000)
        println("Coroutine 4 end: ${Thread.currentThread().name}")
        4
    }

    try {
        val result = listOf(
            deferred1,
            deferred2,
            deferred3,
            deferred4,
        ).awaitAll()
        println("Results: $result")
    } catch (e: Exception) {
        println("Caught exception: $e")
    }

    println("Main end: ${Thread.currentThread().name}")
}

//main start
// dispatching  coroutine main
// running in custom dispatcher
// coroutine 1 start

// dispatching  coroutine main
// running in custom dispatcher
// coroutine 2 start

// dispatching  coroutine main
// running in custom dispatcher
// coroutine 3 start

// dispatching  coroutine main
// running in custom dispatcher
// coroutine 4 start
// coroutine 1 end
//coroutine 3 end
//coroutine 4 end

// caught exception

//main end