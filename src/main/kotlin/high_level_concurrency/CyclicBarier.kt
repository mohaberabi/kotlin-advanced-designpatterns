package org.example.high_level_concurrency

import arrow.fx.coroutines.CyclicBarrier
import kotlinx.coroutines.*


private fun CoroutineScope.fetchAsync(data: String) = async {
    delay(500L)
    data
}


fun main() {
    val barier = CyclicBarrier(3)

    runBlocking(Dispatchers.IO) {
        ('a'..'x').forEachIndexed { index, c ->

            launch {
                fetchAsync("Letter :$c")
                barier.await()
                println("fetched letter :$c at ${System.currentTimeMillis() % 1000}")
            }

        }
    }
}