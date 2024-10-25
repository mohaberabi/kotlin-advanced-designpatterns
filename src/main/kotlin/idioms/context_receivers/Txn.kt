package org.example.idioms.context_receivers

interface Transaction {


    fun commit()
    fun rollback()
}

interface UserTable {
    fun insert()
    fun delete()
}

fun Transaction.createUser(table: UserTable) {
    try {
        table.insert()
        commit()
    } catch (e: Exception) {

    }
}

fun UserTable.createUser(txn: Transaction) {
    try {
        insert()
        txn.commit()
    } catch (e: Exception) {

    }
}

context(Transaction)
fun UserTable.createUserBetter() {
    try {
        insert()
        commit()
    } catch (e: Exception) {

    }

}

fun transaction(func: Transaction.() -> Unit) {

}


val table = object : UserTable {
    override fun insert() {
        TODO("Not yet implemented")
    }

    override fun delete() {
        TODO("Not yet implemented")
    }

}

context(Transaction, UserTable)
fun createUserBetter2() {
    try {
        insert()
        commit()
    } catch (e: Exception) {

    }

}

fun main() {
    transaction {
        with(table) {
            createUserBetter2()
        }
    }

}