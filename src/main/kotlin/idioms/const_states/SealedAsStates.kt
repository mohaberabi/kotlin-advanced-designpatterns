package org.example.idioms.const_states

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay


sealed interface PizzaSealedState {
    val orderId: Int
    fun next(): PizzaSealedState
}


data class OrderTakenState(
    override val orderId: Int,
) : PizzaSealedState {
    override fun next(): PizzaSealedState = OrderMakingState(orderId)
}

data class OrderMakingState(
    override val orderId: Int,
) : PizzaSealedState {
    override fun next(): PizzaSealedState = OrderOutState(orderId)

}

data class OrderOutState(
    override val orderId: Int,
) : PizzaSealedState {
    override fun next(): PizzaSealedState = OrderDoneState(orderId)

}

data class OrderDoneState(
    override val orderId: Int,
) : PizzaSealedState {
    override fun next(): PizzaSealedState = this

}

suspend fun main() {

    var state: PizzaSealedState = OrderTakenState(123)
    while (state !is OrderDoneState) {
        state = state.next()
        delay(1000L)
        println(state)
    }
}