package org.example.paradigms.coroutines.channels

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private val channel = Channel<Int> { }
fun main() {


    runBlocking {
        launch {
            channel.consumeEach {
                
            }
        }
    }

}