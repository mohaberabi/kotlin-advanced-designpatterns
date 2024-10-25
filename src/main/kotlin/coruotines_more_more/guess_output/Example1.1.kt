package org.example.coruotines_more_more.guess_output

import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Start")
    launch {
        println("Inside Coroutine")
    }
    println("End")
}

// start
// end
//inside