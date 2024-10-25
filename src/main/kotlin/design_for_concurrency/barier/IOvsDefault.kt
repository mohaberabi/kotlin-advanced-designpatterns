package org.example.design_for_concurrency.barier

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {

//
//    runBlocking {
//        repeat(1000) {
//            launch(Dispatchers.Default) {
//                println(Thread.currentThread().name)
//            }
//        }
//    }
    runBlocking {
        repeat(1000) {
            launch(Dispatchers.IO) {
                println(Thread.currentThread().name)
            }
        }
    }
}