package org.example.paradigms.concureent

import java.util.concurrent.CountDownLatch
import kotlin.concurrent.thread


fun main() {


    var counter = 0
    val latch = CountDownLatch(100_000)
    repeat(100) {
        thread {
            repeat(1000) {
                synchronized(latch) {
                    counter++
                    latch.countDown()
                }

            }
        }
    }
    latch.await()
    println("COUNTER :${counter}")
}