package org.example.design_for_concurrency.barier

import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis


data class FavoriteCharacter(


    val name: String,
    val catchPhrase: String,
    val pic: ByteArray = Random.nextBytes(42)
)


private fun CoroutineScope.getCatchPhraseAsync(
    name: String,
) = async {
    delay(1000L)
    name
}

private fun CoroutineScope.getPictureAsync(
    size: Int,
) = async {
    delay(500L)
    ByteArray(size)
}

private suspend fun fetchCharacterSequentially(
    name: String,
    bytes: Int
): FavoriteCharacter = coroutineScope {
    val phrase = getCatchPhraseAsync(name).await()
    val pic = getPictureAsync(bytes).await()
    FavoriteCharacter(name, phrase, pic)
}

private suspend fun fetchCharacterParallel(
    name: String,
    bytes: Int
): FavoriteCharacter = coroutineScope {
    val phrase = getCatchPhraseAsync(name)
    val pic = getPictureAsync(bytes)
    FavoriteCharacter(name, phrase.await(), pic.await())
}

fun main() {
    runBlocking {
        val timeInSequential = measureTimeMillis {
            val char = fetchCharacterSequentially("", 1)
        }
        val timeParallel = measureTimeMillis {
            val char = fetchCharacterParallel("", 1)
        }
        println(timeInSequential)
        println(timeParallel)

    }


}