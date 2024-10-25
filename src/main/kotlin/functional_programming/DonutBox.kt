package org.example.functional_programming


class NoSpaceInBoxException : Exception("No Space int the donut box ")
data class Donut(
    val name: String,
    val calories: Int,
    val allergens: List<String> = listOf()
)

class DonutBox(private val capacity: Int) {


    private val donuts = mutableListOf<Donut>()


    fun addDonut(donut: Donut) {
        if (donuts.size < capacity) {
            donuts.add(donut)
        } else {
            throw NoSpaceInBoxException()
        }
    }

    fun removeDonut(donut: Donut) {
        donuts.remove(donut)
    }
}

fun main() {
    val box = DonutBox(0)

    try {
        box.addDonut(Donut("choco", 200))
    } catch (e: Exception) {
        println(e)
    }
}