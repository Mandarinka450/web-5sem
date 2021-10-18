package com.example.a3_laba

fun tryToSetOperand(operand: String, isFirst:Boolean){
    if (!isFirst)
        try {
            Calculator.second = operand.toDouble()
        } catch (e: Exception) {
            Calculator.second = null
        }
    else
        try {
            Calculator.first= operand.toDouble()
        } catch (e: Exception) {
            Calculator.first = null
        }
}

class Calculator {
    companion object {

        var first: Double? = null
        var second: Double? = null
        var operation: String = ""

        fun calculate(): Double?{
            var result: Double? = null
            if (first != null && second != null) {
                when (operation) {
                    "+" -> result = first!! + second!!
                    "-" -> result = first!! - second!!
                    "*" -> result = first!! * second!!
                    "/" -> result = first!! / second!!
                }
            }
            return result
        }
    }
}

