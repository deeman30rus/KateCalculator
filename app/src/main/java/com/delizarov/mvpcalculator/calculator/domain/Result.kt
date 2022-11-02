package com.delizarov.mvpcalculator.calculator.domain

sealed class CalculationResult {
    class Error(val error: Throwable): CalculationResult()
    class Success(val value: Int): CalculationResult()
}