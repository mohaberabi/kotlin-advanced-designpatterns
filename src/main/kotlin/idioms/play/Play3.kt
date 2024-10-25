package org.example.idioms.play

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


suspend fun main() {


    coroutineScope {
        launch {
            println("first scope first launch before delay")//1
            println("first scope first launch -2 before delay ")//2
            delay(3000L)//5
            println("first scope first launch after delay")//6
        }
        launch {
            println("first scope second launch before delay")//3
            println("first scope second launch -2 before delay ")//4
            delay(3000L)
            println("first scope second launch after delay")//7
        }
    }
    coroutineScope {
        launch {
            println("second scope first launch before delay")
            println("second scope first launch -2 before delay ")
            delay(2000L)
            println("second scope first launch after delay")
        }
        launch {
            println("second scope second launch before delay")
            println("second scope second launch -2 before delay ")
            delay(2000L)
            println("second scope second launch after delay")
        }
    }
    println("not async 1 ")
    println("not async 2 ")

}