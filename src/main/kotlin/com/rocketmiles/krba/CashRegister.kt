package com.rocketmiles.krba

import java.util.Random

/**
 *
 */
class CashRegister(_20: Int = 1, _10: Int = 2, _5: Int = 3, _2:Int = 4, _1:Int = 5) {

    // The register Till, is a HasMap containing the denomination as a key and
    // a number representing the quantity of that denomination as the value
    private val till: MutableMap<Int, Int> = mutableMapOf((20 to _20), (10 to _10), (5 to _5), (2 to _2), (1 to _1))

    // Convenience property
    private val denominations:List<Int> = listOf(20,10,5,2,1)

    /**
     * Name: show
     * Arguments: None
     * Return: Unit
     * Description: show() computes the total $ amount currently represented in the register Till. It then prints a
     *              string displaying the current total,
     */
    fun show():String {
        val total = getTotal()
        val tillString = getTillString()
        val result = "${"$"}$total $tillString"
        println(result)
        return result // adding a return value for the sake of testing..
    }

    /**
     * Name: putCash
     * @param: values, List<Int> - A list of size 5 containing the count of each denomination.
     * @return: Unit
     * Description: Adds the values in the list to the 'Till'. Values are assumed to be in the following order
     *              20, 10, 5, 2, 1 respectively. Displays the new till results once till has been updated
     */
    fun putCash(values:List<Int>) {
        if (values.size < 5) {
            println("Error! Only ${values.size} values of 6 provided $values ")
            return
        } else {
            for((i, k) in denominations.withIndex()) {
                val num = till.get(k) ?: values.get(i)
                till.put(k, values[i] + num)
            }
            show()
        }
    }

    /**
     * @name: takeCash
     * Arguments: values, List<Int> - A list of size 5 containing the count of each denomination.
     * Return: Unit
     * Description: Adds the values in the list to the 'Till'. Values are assumed to be in the following order
     *              20, 10, 5, 2, 1 respectively. Displays the new till results once till has been updated
     */
    fun takeCash(values:List<Int>) {
        val tempTill = HashMap(till)  // generate a temporary 'Till' to operate on
        var failed = false  // A control switch
        if (values.size < 5) {
            println("Error! Only ${values.size} values of 6 provided $values ")
            return
        } else {
            for((i, k) in denominations.withIndex()) {
                val num = tempTill[k] ?: 0
                if ((num - values[i]) < 0) {
                    failed = true
                    break
                } else
                    tempTill.put(k, num - values[i] )
            }
            if (failed)
                println("Error! Insufficient funds!!")
            else {
                for ((k,v) in tempTill.entries) till.put(k, v)
                show()
            }
        }
    }

    /**
     * @name: makeChange
     * @param: amount, Int -
     * @return
     */
    fun makeChange(amount:Int): String {
        val solutionSpace: MutableList<Map<Int, Int>> = mutableListOf()
        findSolutions(amount, 0, mutableListOf(), null, solutionSpace) // it returns the number of iterations, but we dont need it for now
        val result: String
        val viable = solutionSpace.filter{ sol ->
            denominations.map { k -> if (till.getValue(k) >= sol.getValue(k)) 1 else 0 }.sum() > 4
        }
        if (viable.isNotEmpty()) {
            val tempTill = HashMap(till)
            val values = viable[Random().nextInt(viable.size)]
            // The actual printing of the solution
            result = denominations.map { "${values[it]}" }.joinToString(separator = " ") { it }

            for(k in denominations) {
                val num = tempTill[k] ?: 0
                tempTill.put(k, num - values.getValue(k) )
            }
            for ((k,v) in tempTill.entries) till.put(k, v)

        } else
            result = "Sorry, cant make change!"

        println(result)
        return result
    }

    /**
     * @name: empty
     * @description: convenience function to empty the till, largely for debugging purposes
     */
    fun empty() {
        denominations.forEach { till.put(it, 0) }
        show()
    }

    /**
     * @name: findSolutions
     * @param: amount, Int - The running amount of change to generate
     * @param: _index, Int - The current placeholder index for our denominations
     * @param: combinations, MutableList<Pair<Int, Int>>:nullable - Contains a local list of solutions to the change making problem
     * @param: solutions, MutableList<Map<Int, Int>> - A global list of all solutions found
     *
     * @return: Integer, Recursive
     * Notes: This solution is drawn heavily from this SO answer: https://stackoverflow.com/a/1106973 @ leif
     *        I am more than happy to talk about it
     *        - KRA
     */
    private fun findSolutions(amount:Int, _index:Int, combination:MutableList<Pair<Int, Int>>, pair:Pair<Int, Int>?, solutions:MutableList<Map<Int, Int>>): Int {
        var index = _index // make a mutable copy
        if (pair != null) {
            combination.add(pair)
        }
        if (amount == 0 || (index + 1) == denominations.size) {
            if ((index+1) == denominations.size && amount > 0) {
                combination.add(Pair(denominations[index], amount))
                index += 1
            }
            while( index < denominations.size) {
                combination.add(Pair(denominations[index], 0))
                index += 1
            }
            solutions.add(combination.toMap())
            return 1
        }
        val cur = denominations[index]
        return (0..amount/cur).map {
            findSolutions(amount-cur*it, index+1, combination.toMutableList(), Pair(cur, it), solutions)
        }.sum()
    }

    /************************************************ PRIVATE METHODS ************************************************/
    /**
     * @name: getTotal
     * @param: None
     * @return: Int - The total amount of money in the CashRegister
     * Description: Helper function used to calculate and return the total amount of money in the CashRegister
     */
    private fun getTotal():Int {
        var total = 0
        for ((k,v) in till.entries) total += k * v
        return total
    }

    /**
     * @name: getTillString
     * @param: None
     * @return: String -
     */
    private fun getTillString():String =  denominations.map { "${till[it]}" }.joinToString(separator = " ") { it }


}