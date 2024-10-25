package org.example.paradigms.coroutines.flows

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flow
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

    val debouncedFlow = flow.debounce(500L)
    runBlocking {
        debouncedFlow.collect { no ->
            println("received :$no")
        }
    }
}