package com.rocketmiles.krba
import kotlin.io.readLine


/**
 * App.kt
 * Contains the solution to the assignment 'HelloChange' by RocketMiles Inc
 *
 * Author: Kyle R. Almryde
 * Date: 11/12/17
 */
fun main(args: Array<String>) {
    println("Ready!")
    val register = CashRegister()
    val clerk = Clerk()

    while(true) {
        print("> ")
        clerk.processRequest(readLine())

        when (clerk.getCommand()) {
            "show" -> {
                register.show()
            }
            "put" -> {
                register.putCash( clerk.getValues() )
            }
            "take" -> {
                register.takeCash( clerk.getValues() )
            }
            "change" -> {
                register.makeChange( clerk.getAmount() )
            }
            "quit" -> {
                println("Bye")
                return
            }
            else -> {
                println("Oops, try again")
            }
        }
    }
}

