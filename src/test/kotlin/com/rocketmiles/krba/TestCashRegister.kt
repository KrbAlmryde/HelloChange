package com.rocketmiles.krba

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.*
import kotlin.test.assertEquals


import com.rocketmiles.krba.CashRegister


object CashRegisterSpec: Spek({
    given("a cash register"){
        var register = CashRegister()
        on("show") {
            it("should return a string displaying the total and a series of 5 values"){
                assertEquals("\$68 1 2 3 4 5", register.show())
            }
        }

        on("put") {
            register.putCash(listOf(1, 2, 3, 0, 5))
            it("should display $128 2 4 6 4 10") {
                assertEquals("\$128 2 4 6 4 10", register.show())
            }
        }

        on("take") {
            register.takeCash(listOf(2, 4, 6, 4, 10))
            it("should display $0 0 0 0 0 0") {
                assertEquals("\$0 0 0 0 0 0", register.show())
            }
        }

        on("Make Change") {
            register.makeChange(13)
            it("should not change the result [insufficient funds!]") {
                assertEquals("\$0 0 0 0 0 0", register.show())
            }

            register.putCash(listOf(1, 0, 3, 4, 0))
            it("should be $43 1 0 3 4 0") {
                assertEquals("\$43 1 0 3 4 0", register.show())
            }

            it("change should look like 0 0 1 3 0") {
                assertEquals("\$43 1 0 3 4 0", register.makeChange(13))
            }

            it("should adjust the total to be $32 1 0 2 1 0") {
                assertEquals("\$43 1 0 3 4 0", register.show())
            }
        }

        on ("Make $8 out of $13"){}
    }
})