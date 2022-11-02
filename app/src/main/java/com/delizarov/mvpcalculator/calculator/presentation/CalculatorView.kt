package com.delizarov.mvpcalculator.calculator.presentation

import com.delizarov.mvpcalculator.MvpView
import com.delizarov.mvpcalculator.calculator.domain.CalculationResult

interface CalculatorView: MvpView {

    val expression: String

    fun showResult(result: CalculationResult)
}