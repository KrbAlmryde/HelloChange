package com.rocketmiles.krba

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.*
import kotlin.test.assertEquals


object CashRegisterSpec: Spek({
    given("a cash register"){
        val register = CashRegister()
        on("show") {
            it("should return a string displaying the total and a series of 5 values"){
                assertEquals("\$68 1 2 3 4 5", register.show())
            }
        }

        on("put") {
            it("should display $128 2 4 6 4 10") {
                register.putCash(listOf(1, 2, 3, 0, 5))
                assertEquals("\$128 2 4 6 4 10", register.show())
            }
        }

        on("take") {
            it("should display $0 0 0 0 0 0") {
                register.takeCash(listOf(2, 4, 6, 4, 10))
                assertEquals("\$0 0 0 0 0 0", register.show())
            }
        }

        on("Make Change") {
            it("should not change the result [insufficient funds!]") {
                register.makeChange(13)
                assertEquals("\$0 0 0 0 0 0", register.show())
            }

            it("should be $43 1 0 3 4 0") {
                register.putCash(listOf(1, 0, 3, 4, 0))
                assertEquals("\$43 1 0 3 4 0", register.show())
            }

            it("change should look like 0 0 1 3 0") {
                assertEquals("0 0 1 3 0", register.makeChange(11))
            }

            it("should adjust the total to be $32 1 0 2 1 0") {
                assertEquals("\$32 1 0 2 1 0", register.show())
            }
        }

        on ("Make $8 out of $13"){
            it("should be $13 in total") {
                register.empty()
                register.putCash(listOf(0, 1, 0, 1, 1))
                assertEquals("\$13 0 1 0 1 1", register.show())
            }

            it("Should Not be able to make change") {
                assertEquals("Sorry, cant make change!", register.makeChange(8))
            }

            it("Should be able to make change!") {
                register.empty()
                register.putCash(listOf(0, 0, 1, 1, 1))
                assertEquals("0 0 1 1 1", register.makeChange(8))
            }

        }
    }
})