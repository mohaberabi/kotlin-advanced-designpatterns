package org.example.coruotines_more_more.guess_output

import kotlinx.coroutines.*

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught exception: ${exception.message}")
    }

    val scope = CoroutineScope(SupervisorJob() + handler)

    scope.launch {
        throw Exception("Error in coroutine")
    }

    delay(500L)
    println("Continuing execution after exception")
}

// cacugh exception "Error in Coroutine"
// Continuing execution after exception