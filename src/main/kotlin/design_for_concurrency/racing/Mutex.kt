package org.example.design_for_concurrency.racing

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex


private var counter = 0

private var counterMutexed = 0
fun main() = runBlocking<Unit> {

    val jobs = List(10) {

        async(Dispatchers.Default) {
            repeat(1000) {
                counter++ // never 10000
            }
        }

    }
    jobs.awaitAll()

    println(counter)


    val mutex = Mutex()
    val jobs2 = List(10) {

        async(Dispatchers.Default) {
            repeat(1000) {
                mutex.lock()
                counterMutexed++
                mutex.unlock()
            }
        }

    }
    jobs2.awaitAll()
    println(counterMutexed)

}
