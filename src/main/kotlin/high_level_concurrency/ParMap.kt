package org.example.high_level_concurrency

import arrow.fx.coroutines.parMap
import kotlinx.coroutines.*


private val tasks = 'a'..'z'
suspend fun main() {
    val wiki = tasks.parMap {
        fetchAsync(it)
    }.awaitAll()
    for (w in wiki) {
        println(w)
    }
}

private fun CoroutineScope.fetchAsync(query: Char): Deferred<String> = async {
    delay(2000L)
    "www.google.com/$query"
}