package org.example.paradigms.functional


private val inputs = listOf(
    setOf(1, 2, 3),
    setOf(3, 1, 2),
    setOf(2, 1, 3),
    setOf(2, 3, 1),
    setOf(4, 5, 6)
)

private fun sum(nos: Set<Int>): Double = nos.sumOf { it.toDouble() }
private fun summarizer(): (Set<Int>) -> Double {
    val cache = mutableMapOf<Set<Int>, Double>()
    return { nos ->
        cache.computeIfAbsent(nos, ::sum)
    }
}

fun main() {

    val summer = summarizer()
    inputs.forEach {
        println(summer(it))
    }
}