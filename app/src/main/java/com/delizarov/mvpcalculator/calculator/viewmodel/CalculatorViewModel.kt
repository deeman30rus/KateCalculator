package com.delizarov.mvpcalculator.calculator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.delizarov.mvpcalculator.calculator.domain.CalculationResult
import com.delizarov.mvpcalculator.calculator.domain.Expression
import com.delizarov.mvpcalculator.calculator.domain.Interactor

class CalculatorViewModel: ViewModel() {

    private val interactor = Interactor()

    private val _result = MutableLiveData<CalculationResult>()
    val result: LiveData<CalculationResult>
        get() = _result

    fun onCalculateClick(expression: Expression) {
        val calculationResult = interactor.calculate(expression)

        _result.postValue(calculationResult)
    }

}