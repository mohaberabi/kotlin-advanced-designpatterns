package org.example.paradigms.coroutines.channels

import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


fun main() {

    runBlocking {
        val actor = actor<Long> {
            var prev = 0L
            channel.consumeEach {
                println(it - prev)
                prev = it
                delay(100L)
            }
        }
        repeat(10) {
            actor.send(System.currentTimeMillis())
        }
        actor.close().also { println("Sent Done ") }
    }
}