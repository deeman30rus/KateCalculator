package com.delizarov.mvpcalculator.calculator.presentation

import com.delizarov.mvpcalculator.BasePresenter
import com.delizarov.mvpcalculator.calculator.domain.Interactor

class CalculatorPresenter: BasePresenter<CalculatorView>() {

    private val interactor = Interactor()

    fun onCalculateButtonClicked() {
        val result = interactor.calculate(view.expression)

        view.showResult(result)
    }
}