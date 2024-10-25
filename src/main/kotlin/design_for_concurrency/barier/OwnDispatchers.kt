package org.example.design_for_concurrency.barier

import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.ForkJoinPool


private val myDispatcher = ForkJoinPool(
    4,
).asCoroutineDispatcher()


fun main() {


    runBlocking {


        repeat(100) {
            launch(myDispatcher) {
                println(Thread.currentThread().name)
            }
        }
    }
}