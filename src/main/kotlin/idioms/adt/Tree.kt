package org.example.idioms.adt


sealed interface Tree<out T>
data object Empty : Tree<Nothing>
data class Node<T>(
    val value: T,
    val left: Tree<T> = Empty,
    val right: Tree<T> = Empty,
) : Tree<T>


fun Tree<Int>.sum(): Long = when (this) {
    Empty -> 0
    is Node -> value + left.sum() + right.sum()
}

fun main() {


    var t = Node(1)
    repeat(100_000) {
        t = Node(1, Empty, t)
    }
    println(t.deepSum())
}

private fun Node<Int>.deepSum(): Long {
    return DeepRecursiveFunction<Tree<Int>, Long> { node ->
        when (node) {
            Empty -> 0
            is Node -> value + callRecursive(node.right) + callRecursive(node.left)
        }
    }(this)
}