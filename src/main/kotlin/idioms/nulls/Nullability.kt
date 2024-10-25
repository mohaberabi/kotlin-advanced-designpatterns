package org.example.idioms.nulls


private data class Some(val id: String?)
private data class SomeOfSome(val some: Some?)


fun main() {


    val some: SomeOfSome? = SomeOfSome(Some(null))
    val someOfSome = some?.some?.id?.length
    println(someOfSome)

}