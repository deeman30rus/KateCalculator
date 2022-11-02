package com.delizarov.mvpcalculator.calculator.domain

sealed interface Term {

    class Number(val number: Int) : Term

    enum class Operand(
        val operator: Char,
        val priority: Int,
    ) : Term {
        Sum('+', 1),
        Subtract('-', 1),
        Multiply('*', 2),
        Divide('/', 2);

        companion object {

            fun createFromChar(char: Char): Operand? {
                return values().find { it.operator == char }
            }
        }
    }
}