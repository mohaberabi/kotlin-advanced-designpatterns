package org.example.idioms.validation


private fun setCapacity(cap: Int) {
    require(cap > 0)
}


private fun printData(s: String?) {
    requireNotNull(s)
    println(s)
}

private fun customRequire(predicate: Boolean) {
    if (!predicate) {
        throw IllegalArgumentException("Failed Requirement")
    }
}

private fun doDummyThing(x: Int) {
    customRequire(x > 100)
}

fun main() {

    try {
        doDummyThing(0)
        setCapacity(1)
        printData("")
    } catch (e: Exception) {
        println(e)
    }
}