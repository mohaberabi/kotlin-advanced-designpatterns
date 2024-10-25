package org.example.coruotines_more_more.guess_output

import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Start")

    launch {
        delay(1000L)
        println("Inside Coroutine After Delay")
    }
    println("End")
}
// start
//end
// inside coroutine after delay