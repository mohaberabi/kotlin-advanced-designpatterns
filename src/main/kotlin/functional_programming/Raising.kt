package org.example.functional_programming

import arrow.core.raise.Raise
import arrow.core.raise.fold


data class DonutBoxRaise(
    val capacity: Int,
    val donuts: MutableList<Donut> = mutableListOf()
)


fun Raise<NoSpaceInBox>.addDonut(
    box: DonutBoxRaise,
    donut: Donut
): DonutBoxRaise {
    if (box.capacity > box.donuts.size) {
        box.donuts.add(donut)
        return box
    } else {
        raise(NoSpaceInBox)
    }
}

fun Raise<NoSuchDonut>.removeDonut(
    box: DonutBoxRaise,
    donut: String
): Donut {
    box.donuts.find { it.name == donut }?.let {
        box.donuts.remove(it)
        return it
    }
    raise(NoSuchDonut(donut))
}

fun main() {

    val box = DonutBoxRaise(1)
    val donut = Donut("Choco", 1)
    fold(
        {
            addDonut(box, donut)
        },
        {


                error: NoSpaceInBox ->
            println(error)
        },
        {
            fold(
                {
                    removeDonut(box, donut.name)
                },
                { error: NoSuchDonut ->
                    println(error)
                },
                { donut ->
                    println(donut)
                },
            )
        },
    )
}