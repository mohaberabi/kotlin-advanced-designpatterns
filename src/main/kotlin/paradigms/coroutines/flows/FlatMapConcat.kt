package org.example.paradigms.coroutines.flows

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking


private val flow1 = listOf(1, 2, 3, 4, 5)
private val flow2 = listOf("One", "Two", "Three", "Four", "Five")
private val flow3 = listOf("واحد", "اثنين", "ثلاثة", "اربعة", "خمسة")


private fun explainFlatMapConcat() {


    for (int in flow1) {
        for (let in flow2) {
            for (ar in flow3) {
                println(
                    "${int}-${let}-${ar}"
                )
            }
        }
    }
}

fun main() {


    explainFlatMapConcat()
//    runBlocking {
//
//        val concated = flow1.asFlow().flatMapConcat { int ->
//            flow2.asFlow().flatMapConcat { letter ->
//
//                flow3.asFlow().map {
//                    "${int}-${letter}-${it}"
//                }
//            }
//        }
//
//        concated.collect { value ->
//            println(value)
//        }
//    }
}