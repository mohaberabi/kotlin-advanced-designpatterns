package org.example.design_for_concurrency.racing

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex


fun main() = runBlocking {


    val mutexA = Mutex()
    val mutexB = Mutex()
    val job1 = launch {
        mutexA.lock()
        delay(1000)
        println("Coroutine 1 : Acquired Mutex A Now Attempting To Acquire mutex B")
        mutexB.lock()
        println("Coroutine 1 : Acquired Mutex B ")
        mutexB.unlock()
        mutexA.unlock()
    }
    val job2 = launch {
        mutexB.lock()
        delay(1000)
        println("Coroutine 2 : Acquired Mutex B Now Attempting To Acquire mutex A ")
        mutexA.lock()
        println("Coroutine 2 : Acquired Mutex A ")
        mutexB.unlock()
        mutexA.unlock()
    }
    job1.join()
    job2.join()
}