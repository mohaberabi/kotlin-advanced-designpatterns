package org.example

import kotlinx.coroutines.*

suspend fun main() {

    dummy2()
    println("Before")


}

suspend fun dummy2() {
    delay(5000L)
    println("dummy2")
}

suspend fun dummy() {
    CoroutineScope(Dispatchers.IO).launch {
        delay(5000L)
        println("inside launch")
    }.join()
    println("outside launch")
}