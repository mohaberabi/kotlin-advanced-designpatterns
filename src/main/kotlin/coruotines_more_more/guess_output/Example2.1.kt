package org.example.coruotines_more_more.guess_output

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println("Start")

    launch {
        delay(1000L)
        println("Inside Coroutine After Delay")
    }.join()
    println("End")
}
// start
// inside coroutine after delay
//end
