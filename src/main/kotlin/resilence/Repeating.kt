package org.example.resilence

import arrow.resilience.Schedule


val doneAndFail = sequence {

    yield("ok")
    yield("ok")
    yield("ok")
    while (true) {
        yield { throw RuntimeException() }
    }
}.iterator()

suspend fun main() {

    val result = Schedule.recurs<Unit>(10).repeatOrElse(
        block = {
            println(doneAndFail.next())
        },
        orElse = { e, atempts ->
            println("Faild on the attempt${atempts?.inc() ?: 0} with $e")
            -1
        }
    )
    println(result)
}