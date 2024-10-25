package org.example.high_level_concurrency

import arrow.fx.coroutines.parZip
import kotlinx.coroutines.*
import kotlin.random.Random


private fun CoroutineScope.getQuote(
): Deferred<String> = async {
    delay(Random.nextLong(1000L, 3000L))
    "Such Loser"
}

suspend fun main() {
    coroutineScope {
        parZip(
            { getQuote().await() },
            { getQuote().await() },
            { getQuote().await() },
        ) { one, two, three ->
            println("Quotes are $one - $two - $three")

        }
    }


}