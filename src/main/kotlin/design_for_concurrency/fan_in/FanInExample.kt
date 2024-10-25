package org.example.design_for_concurrency.fan_in

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking


private fun CoroutineScope.doWorkAsync(
    channel: ReceiveChannel<String>,
    resultChannel: Channel<String>
) = async(Dispatchers.Default) {

    for (p in channel) {
        resultChannel.send(p.repeat(2))
    }
}

private fun CoroutineScope.generateWork() = produce<String> {
    for (i in 1..10_000) {
        send("page $i")
    }
    close()
}

fun main() {
    runBlocking {


        val workChannel = generateWork()
        val resultChannel = Channel<String>()
        val workers = List(10) {
            doWorkAsync(
                channel = workChannel,
                resultChannel = resultChannel
            )
        }
        resultChannel.consumeEach {
            println(it)
        }
    }
}