package org.example.functional_programming

import arrow.core.Ior
import arrow.core.bothIor
import arrow.core.raise.ensure
import arrow.core.raise.ior
import arrow.core.right
import arrow.core.rightIor


data class Object(
    val space: Long,
)

interface MemoryError
object OutOfMemory : MemoryError
object AlmostOutOfMemory : MemoryError

class RAM(
    private var notifyWhen: Long,
    memorySpace: Long
) {

    private var space = memorySpace
    fun allocate(objekt: Object): Ior<MemoryError, RAM> {

        return ior(
            combineError = { error, both -> both },
        ) {
            ensure(space > objekt.space) { OutOfMemory }
            space -= objekt.space
            return if (space == notifyWhen) {
                (AlmostOutOfMemory to this@RAM).bothIor()
            } else {
                (this@RAM).rightIor()
            }
        }

    }
}


fun main() {
    val ram = RAM(
        notifyWhen = 2000L,
        memorySpace = 4084L
    )
    val someObject = Object(2000L)
    var ior = ram.allocate(someObject)
    when (ior) {
        is Ior.Both -> print("memory is almost out of space but object allocated")
        is Ior.Left -> println("memory out of space ")
        is Ior.Right -> println("object allocated , with memory available ")
    }
    ior = ram.allocate(someObject)
    when (ior) {
        is Ior.Both -> print("memory is almost out of space but object allocated")
        is Ior.Left -> println("memory out of space ")
        is Ior.Right -> println("object allocated , with memory available ")
    }
    ior = ram.allocate(someObject)
    when (ior) {
        is Ior.Both -> print("memory is almost out of space but object allocated")
        is Ior.Left -> println("memory out of space ")
        is Ior.Right -> println("object allocated , with memory available ")
    }

}