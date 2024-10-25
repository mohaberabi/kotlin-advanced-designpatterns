package org.example.paradigms.coroutines.flows

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.sample
import kotlinx.coroutines.runBlocking


private val flow = flow<Int> {


    var i = 0
    repeat(20) {
        emit(++i)
        delay(100)
    }

}

@OptIn(FlowPreview::class)
fun main() {

    val sampleFlow = flow.sample(500L)
    runBlocking {
        sampleFlow.collect { no ->
            println("received :$no")
        }
    }
}