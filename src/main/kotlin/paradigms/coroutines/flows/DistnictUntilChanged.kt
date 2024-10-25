package org.example.paradigms.coroutines.flows

import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking


private val flow = flowOf(1, 1, 2, 2, 1, 1, 3, 3, 4, 4, 4, 4, 4, 5, 6, 7, 8, 9)


fun main() {


    runBlocking {
        flow.distinctUntilChanged().collect {
            //1,2,1,3,4,5,6,7,8,9

            println(it)
        }
    }
}