package org.example.functional_programming

import arrow.core.Either
import arrow.core.left
import arrow.core.raise.either
import arrow.core.right


object NoSpaceInBox


data class NoSuchDonut(val name: String)


class FunctionalDonutBox(
    private val capacity: Int,
) {
    private val donuts = mutableListOf<Donut>()
    fun addDonut(
        donut: Donut,
    ): Either<NoSpaceInBox, FunctionalDonutBox> {
        return if (donuts.size < capacity) {
            donuts.add(donut)
            right()
        } else {
            NoSpaceInBox.left()
        }

    }

    fun removeDonut(
        name: String,
    ): Either<NoSuchDonut, Donut> {
        return donuts.find { it.name == name }?.let {
            donuts.remove(it)
            it.right()
        } ?: NoSuchDonut(name).left()

    }
}

fun main() {

//    val box = FunctionalDonutBox(1)
//    val res = box.removeDonut("s")
//    res.fold(
//        ifLeft = { println(it) },
//        ifRight = {},
//    )
//

    val result: Either<Any, Donut> = either {
        val donutBox = FunctionalDonutBox(1)
        val donut = Donut("donut", 1)


        donutBox.addDonut(donut).bind()
        donutBox.removeDonut(donut.name).bind()
    }
    result.fold(
        ifLeft = {},
        ifRight = { println(it) },
    )
    val donut = result.map { it }
    println(donut)
}