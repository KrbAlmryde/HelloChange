package com.rocketmiles.krba

class Clerk {
    private val register = CashRegister()

    fun processTransaction() {
        val input = readLine()
        if (input != null) {

        }

    }

    fun run() {
        while(true) {
            print("> ")
            processTransaction()

        }
    }
}