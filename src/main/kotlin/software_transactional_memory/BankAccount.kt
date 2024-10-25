package org.example.software_transactional_memory

import arrow.fx.stm.STM
import arrow.fx.stm.TVar
import arrow.fx.stm.atomically


class BankAccount private constructor(
    private val startBalance: Double
) {

    companion object {
        suspend operator fun invoke(startBalance: Double): TVar<BankAccount> {
            return TVar.new(BankAccount(startBalance))

        }

    }

    private var _balance = startBalance

    fun transfer(
        bal: Double,
        to: BankAccount,
    ) {
        _balance -= bal
        to.accept(bal)
    }

    fun accept(bal: Double) {
        _balance += bal
    }

    val balance get() = _balance
    fun printBalance() = println("Your balance :${_balance}")
}

private fun STM.transferMoney(
    myAccount: TVar<BankAccount>,
    amount: Double,
    friendAccount: TVar<BankAccount>,
) {
    val account = myAccount.read()
    require(amount < account.balance)
    val friend = friendAccount.read()
    account.transfer(amount, friend)
    myAccount.modify { account }
}


suspend fun main() {
    val me = BankAccount(2000.0)

    val other = BankAccount(5000.0)
    atomically {
        transferMoney(
            me, 1900.0, other,
        )
    }
    me.unsafeRead().printBalance()
    other.unsafeRead().printBalance()
    atomically {
        transferMoney(
            me, 1900.0, other,
        )
    }
}