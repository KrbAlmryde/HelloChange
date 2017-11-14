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
    val register = CashRegister(1, 2, 3, 4, 5)

    while(true) {
        print("> ")
        val input: List<String>? = readLine()?.split(" ")

        if (input != null) {

            val command = input.first()
            val values: List<Int> = input.subList(1, input.lastIndex+1).map { it.toInt() }

            when (command) {
                "show" -> {
                    register.show()
                }
                "put" -> {
                    register.putCash( values )
                }
                "take" -> {
                    register.takeCash( values )
                }
                "change" -> register.makeChange(values[0])
                "quit" -> {
                    println("Bye")
                    return
                }
            }
        }

    }
}