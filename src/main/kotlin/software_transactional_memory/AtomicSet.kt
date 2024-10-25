package org.example.software_transactional_memory

import arrow.fx.stm.TSet
import arrow.fx.stm.atomically


suspend fun main() {

    val set = TSet.new<String>()

    try {
        atomically {
            set.insert("mohab")
            set.insert("basil")
        }
        atomically {
            set.insert("mohamed")
            set.insert("ali")
            throw RuntimeException()
        }

    } catch (_: Exception) {

    }
    atomically {
        println(set.member("mohab"))
        println(set.member("basil"))
        println(set.member("mohamed"))
        println(set.member("ali"))
    }
}