package org.example.paradigms.coroutines.flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking


private suspend fun searchResult(query: String): Flow<String> {


    val res = listOf(
        "whatever when we call loser it will be for sure mohab",
        "kotlin is the best language",
        "IOS is bad",
        "Android is great",
        "Java is very very very good programming language"
    )

    delay(500)
    return res.filter { query.lowercase() in it }.asFlow()
}

private val userSearch = flow<String> {
    delay(20)
    emit("mohab")
    delay(10)
    emit("Java")
    delay(100)
    emit("good")
    delay(40)
    emit("l")
    delay(40)
    emit("o")
    delay(40)
    emit("ser")
}

fun main() {


    runBlocking {
        val flow = userSearch.flatMapLatest {
            searchResult(it)
        }
        flow.collect {
            println(it)
        }
    }

}