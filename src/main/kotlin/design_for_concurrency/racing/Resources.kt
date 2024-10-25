package org.example.design_for_concurrency.racing


private data class RestaurantBranch(
    val name: String = "branch",
    val earn: Double = 2000.00
) {

    fun close() {
        println("Your earnings today are $earn")
    }

}


private data class HeadQuarter(
    val branches: List<RestaurantBranch> = listOf()
) : AutoCloseable {
    override fun close() {
        val total = branches.sumOf { it.earn }
        println("Your total branches earnings = $total")
        branches.forEach { it.close() }
        println("Closed the all branches")
    }
}

fun main() {
    val branches = listOf(RestaurantBranch())
    val head = HeadQuarter(branches)
    head.use { hq ->
    }
}