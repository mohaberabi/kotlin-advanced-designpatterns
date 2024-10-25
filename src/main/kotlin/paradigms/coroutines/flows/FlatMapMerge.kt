package org.example.paradigms.coroutines.flows

import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking


fun main() {

    val flow1 = ('A'..'Z').map { it.toString() }.asFlow()
    val flow2 = ('a'..'b').map { it.toString() }.asFlow()


    runBlocking {
        flow1.flatMapMerge { one -> flow2.map { "$one-$it" } }.collect {
            println(it)
        }

    }
}