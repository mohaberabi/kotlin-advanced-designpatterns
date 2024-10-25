package org.example.design_for_concurrency.racing

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

private suspend fun getWeather(
    wait: Long,
    id: String
): String {
    delay(wait)
    return "$wait C from the $id"
}

private val channel1 = Channel<String>()
private val channel2 = Channel<String>()

fun main() = runBlocking {


    launch {
        val weather1 = getWeather(500L, "1")
        channel1.send(weather1)
    }

    launch {
        val weather2 = getWeather(500L, "2")
        channel2.send(weather2)
    }

    val winner = select<String> {
        channel1.onReceive { it }
        channel2.onReceive { it }
    }

    println(winner)


}