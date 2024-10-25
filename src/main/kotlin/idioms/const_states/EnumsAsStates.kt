package org.example.idioms.const_states


enum class PizzaOrderStatus {
    OrderReceived,
    PizzaIsMaking,
    OutDelivery,
    Completed;

    fun next() = when (this) {
        OrderReceived -> PizzaIsMaking
        PizzaIsMaking -> OutDelivery
        OutDelivery -> Completed
        Completed -> Completed
    }
}