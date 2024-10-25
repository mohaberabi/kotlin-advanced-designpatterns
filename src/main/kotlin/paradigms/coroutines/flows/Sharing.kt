package org.example.paradigms.coroutines.flows

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


private val originalFlow = flowOf(1, 2, 3, 4, 5).onEach {
    println("Sending $it From ${Thread.currentThread().name}")
}

@OptIn(DelicateCoroutinesApi::class)
val sharedFlow = originalFlow.shareIn(
    scope = CoroutineScope(newFixedThreadPoolContext(4, "My Dispatcher")),
    started = SharingStarted.Lazily,
    replay = 2
)

fun main() {


    runBlocking {
        repeat(5) { id ->
            launch(Dispatchers.Default) {
                sharedFlow.map { value ->
                    println(" Coroutine with id : $id Got a Value : $value on : ${Thread.currentThread()}")
                }.collect()
            }
            delay(100L)
        }

    }
}