package org.example.paradigms.coroutines.flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking


private val flow = flow<Int> {
    var i = 0
    repeat(20) {
        emit(++i)
        delay(100)
    }


}

fun main() {
    var seconds = 0
    runBlocking {

        flow.collect { no ->
            delay(1000L)
            seconds++
            println("Seconds $seconds -> Recieved : ${no}")
        }
    }
    seconds = 0
    println("Start conflating")
    runBlocking {
        flow.conflate().collect { no ->
            delay(1000L)
            seconds++
            println("Seconds $seconds -> Recieved : ${no}")
        }

    }
}