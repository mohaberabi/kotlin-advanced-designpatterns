package org.example.design_for_concurrency.barier

import kotlinx.coroutines.*
import kotlinx.coroutines.awaitAll


private suspend inline fun doSome(
    fail: Boolean,
    block: () -> Unit,
) {
    delay(500L)
    if (fail) {
        throw Exception()
    } else {
        block()
    }
}


fun main() {

    var count = 0
    runBlocking {
        try {
            val list = listOf(
                async { doSome(false) { count++ } },
                async { doSome(true) { count = 100 } }
            )
            println(list.awaitAll())
        } catch (e: Exception) {
            println(count) // 1
        }
    }
    try {
        runBlocking {
            val list = listOf(
                launch { doSome(false) { count++ } },
                launch { doSome(true) { count = 100 } }
            )
            println(list.joinAll())
        }
    } catch (e: Exception) {
        println(count)
    }

}