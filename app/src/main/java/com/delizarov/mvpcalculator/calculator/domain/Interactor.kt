package com.delizarov.mvpcalculator.calculator.domain

import java.lang.Exception

class Interactor {

    private val calculator = Calculator()

    fun calculate(expression: Expression): CalculationResult {
        val trimmed = expression.filter { !it.isWhitespace() }

        return try {
            val value = calculator.calculate(trimmed)

            CalculationResult.Success(value)
        } catch (e: Exception) {
            CalculationResult.Error(e)
        }
    }
}