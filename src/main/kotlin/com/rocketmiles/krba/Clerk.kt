package com.rocketmiles.krba
import java.lang.NumberFormatException


class Clerk {
    private var command: String = ""
    private var arguments:List<Int> = listOf()

    fun processRequest(input:String?) {
        val temp = input.toString().trim().split("\\s+".toRegex())
        command = temp.first()

        arguments = temp.subList(1, temp.lastIndex+1).map {
            try {
                it.toInt()
            }
            catch (e: NumberFormatException) {
                command = "failed"
                0
            }
        }

        if (arguments.size > 1 && (command == "show" || command == "quit")) command = "fail"
        else if ((command == "put" || command == "take") && arguments.size > 5)  command = "fail"
    }

    fun getCommand():String = command
    fun getValues():List<Int> = arguments
    fun getAmount():Int = arguments.first()
    fun getHelp():String = """
            Available Commands:
            show - Displays the current value of the set, as well as the number per denomination
            put [args] -
            take [args] -
            change [arg]
        """
}