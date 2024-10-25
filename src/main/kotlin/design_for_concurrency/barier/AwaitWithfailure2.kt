package org.example.design_for_concurrency.barier

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


private suspend fun getVal(fail: Boolean): String {


    delay(1000L)
    if (fail) {
        throw RuntimeException()
    } else {
        return "loser"
    }
}


fun main() {


    runBlocking {
        val res = async {
            getVal(true)
        }
    }


    runBlocking {
//        val list = listOf(
//            async { getVal(false) },
//            async { getVal(true) },
//        )
//        val res = list.awaitAll()

        val list2 = listOf(
            async { getVal(false) },
            async { getVal(true) },
        )
        val res2 = list2.map { }
    }
}