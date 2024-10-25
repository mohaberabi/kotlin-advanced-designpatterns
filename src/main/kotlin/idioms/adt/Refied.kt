package org.example.idioms.adt

import kotlin.reflect.KClass

//
//
//private fun <T> problemGeneric(a: Number) {
//    if (a is T) {
//        println(a)
//    }
//}
private fun <T : Number> commonApproach(clazz: KClass<T>, a: Number) {
    if (clazz.isInstance(a)) {
        println("yes")
    } else {
        println("no")
    }
}

private inline fun <reified T : Any> printList(list: List<T>) {
    when {
        1L is T -> println("it is long")
        1 is T -> println("it is int ")
        else -> println("some other type")
    }
    println(list)
}

private inline fun <reified T> withReified(a: Number) {
    if (a is T) {
        println(a)
    }
}

fun main() {

    val list = listOf(1, 1L, "m")
    printList(list)
    val list2 = listOf("m", 1, 1L)
    printList(list2)
    val list3 = listOf(1L, "s", 1)
    printList(list3)
}