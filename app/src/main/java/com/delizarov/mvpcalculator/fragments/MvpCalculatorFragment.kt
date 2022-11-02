package com.delizarov.mvpcalculator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.delizarov.mvpcalculator.R
import com.delizarov.mvpcalculator.calculator.domain.CalculationResult
import com.delizarov.mvpcalculator.calculator.presentation.CalculatorPresenter
import com.delizarov.mvpcalculator.calculator.presentation.CalculatorView

class MvpCalculatorFragment: Fragment(R.layout.fragment_mvp_calculator), CalculatorView {

    private val presenter = CalculatorPresenter()

    private lateinit var inputView: EditText
    private lateinit var calculateButton: Button
    private lateinit var resultView: TextView

    override val expression: String
        get() = inputView.text.toString()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mvp_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputView = view.findViewById(R.id.input_expression)
        calculateButton = view.findViewById(R.id.calculate)
        resultView = view.findViewById(R.id.result)

        calculateButton.setOnClickListener {
            presenter.onCalculateButtonClicked()
        }
    }

    override fun onStart() {
        super.onStart()

        presenter.attach(this)
    }

    override fun onStop() {
        super.onStop()

        presenter.detachView()
    }

    override fun showResult(result: CalculationResult) {
        when (result) {
            is CalculationResult.Error -> showError(result.error)
            is CalculationResult.Success -> showCalculationResult(result.value)
        }
    }

    private fun showError(error: Throwable) {
        resultView.text = error.message
    }

    private fun showCalculationResult(value: Int) {
        resultView.text = value.toString()
    }
}