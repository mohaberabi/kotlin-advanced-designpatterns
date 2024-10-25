package org.example.design_for_concurrency.side_kick

import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select


fun main() = runBlocking {


    val batman = actor<String> {
        for (c in channel) {
            println("Batman is Deling With $c")
            delay(100L)
        }
    }
    val robbin = actor<String> {
        for (c in channel) {
            println("Robbin is Deling With $c")
            delay(400L)
        }
    }
    val fight = launch {
        val vills = listOf("Bane1", "Bane2", "Bane3", "Joker1", "Joker2")
        for (vil in vills) {
            val result = select<Pair<String, String>> {
                batman.onSend(vil) {
                    "Batman" to vil
                }
                robbin.onSend(vil) {
                    "Batman" to vil
                }
            }
            delay(90)
            println(result)
        }
    }
}