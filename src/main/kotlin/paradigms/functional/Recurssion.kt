package org.example.paradigms.functional


private tailrec fun sumRec(i: Int, sum: Long, nos: List<Int>): Long {
    return if (i == nos.size) {
        return sum
    } else {
        sumRec(i + 1, nos[i] + sum, nos)
    }
}

fun main() {

    val nos = List(1_000_000) { it }


    println(sumRec(0, 0, nos))
}