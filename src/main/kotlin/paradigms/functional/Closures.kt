package org.example.paradigms.functional


fun counter(): () -> Int {

    var counter = 0
    return { counter++ }
}


fun main() {


    val next = counter()
    println(next())
    println(next())
    println(next())
}