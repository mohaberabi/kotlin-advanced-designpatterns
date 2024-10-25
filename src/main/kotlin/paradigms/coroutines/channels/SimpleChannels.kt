package org.example.paradigms.coroutines.channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


private val channel = Channel<Int> {}


fun main() {

    runBlocking {
        launch {
            for (c in channel) {
                println(c)
            }
        }
        (1..10).forEach {
            channel.send(it)
        }
        // not closing it the main function never returns
        channel.close()

    }


}