package org.example.design_for_concurrency.fan_out

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


private fun CoroutineScope.generateWork() = produce {
    for (i in 1..10_000) {
        send("page $i")
    }
    close()
}


private fun CoroutineScope.dowWork(
    id: Int,
    channel: ReceiveChannel<String>
) = launch(Dispatchers.Default) {
    for (s in channel) {
        println("Worker $id processed $s")
    }
}

fun main() {
    runBlocking {
        val work = generateWork()
        val workers = List(10) {
            dowWork(it, work)
        }
    }
}