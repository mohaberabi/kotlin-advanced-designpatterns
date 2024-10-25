package org.example.resilence

import arrow.resilience.Schedule
import arrow.resilience.retry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking


private fun servicesResponse(): Flow<String> {


    var requests = 0
    var lastErrorTime = System.currentTimeMillis()
    return flow {
        if (requests++ < 3) {
            println("Error ocured at ${System.currentTimeMillis() - lastErrorTime}")
            lastErrorTime = System.currentTimeMillis()
            throw RuntimeException("Something went wrong ")
        } else {
            println("Services is okay")
            emit("OK")
        }
    }
}

fun main() {
    runBlocking {
//        servicesResponse().retry(10).collect {
//            println(it)
//        }
        servicesResponse().retry(Schedule.recurs(10)).toList().also {
            println(it)
        }
    }
}