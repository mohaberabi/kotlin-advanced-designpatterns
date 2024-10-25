package org.example.design_for_concurrency.barier

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis


private suspend fun getValue(): String {
    delay(1000L)
    return "deffered"
}


fun main() {


    runBlocking {
        val vals = listOf(
            async { getValue() },
            async { getValue() },
            async { getValue() },
        )
        val times = measureTimeMillis {
            vals.awaitAll()
        }
        println(times)
        val vals2 = listOf(
            async { getValue() },
            async { getValue() },
            async { getValue() },
        )
        val times2 = measureTimeMillis {
            vals2.map { it.await() }
        }
        println(times2)
    }
}