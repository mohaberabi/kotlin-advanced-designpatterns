package org.example.paradigms.concureent

import paradigms.concureent.IntroToThreads
import kotlin.concurrent.thread


private fun startThreadPooling() {

    repeat(2) {
        thread {
            for (i in 1..100) {
                println("Thread:${it} : ${i}")
            }
        }
    }
}

fun main() {
    val manualThread = thread(start = false) {
        println("I will not start until explicitly started")
    }
    val autoThread = thread {
        println("I am auto starting ")
    }

    manualThread.start()
}