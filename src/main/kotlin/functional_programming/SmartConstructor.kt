package org.example.functional_programming

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure


object NonPositiveCapacity

class PizzaBoxEither private constructor(
    private val capacity: Int,
) {
    companion object {
        operator fun invoke(capacity: Int): Either<NonPositiveCapacity, PizzaBoxEither> {
            return either {

                ensure(capacity > 0) { NonPositiveCapacity }
                PizzaBoxEither(capacity)
            }
        }
    }
}

fun main() {
    when (val box = PizzaBoxEither(0)) {
        is Either.Left -> println(box.value)
        is Either.Right -> println(box.value)
    }
}