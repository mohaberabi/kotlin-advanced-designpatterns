package org.example.idioms.play

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
    runBlocking {
        launch {
            println("inside the launch before delay") //2
            delay(2000L)
            println("inside the launch after delay")//3
        }
        println("inside the scope ")//1
    }

    println("will i be executed first ? noooo")//4
}