package org.example.design_for_concurrency.barier

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


private suspend fun returnOfFail(fail: Boolean): String {


    delay(1000L)
    if (fail) {
        throw RuntimeException()
    } else {
        println("Executed")

        return "val"
    }
}


fun main() {

    runBlocking {
//        val vals = listOf(
//            async { returnOfFail(false) },
//            async { returnOfFail(false) },
//            async { returnOfFail(true) },
//        )
//        val times = measureTimeMillis {
//            vals.awaitAll()
//        }
//        println(times)
        val vals2 = listOf(
            async { returnOfFail(false) },
            async { returnOfFail(false) },
            async { returnOfFail(true) },
        )
        val times2 = measureTimeMillis {
            vals2.map { it.await() }
        }
        println(times2)
    }
}