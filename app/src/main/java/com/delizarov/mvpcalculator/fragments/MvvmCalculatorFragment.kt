package com.delizarov.mvpcalculator.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.delizarov.mvpcalculator.calculator.domain.CalculationResult
import com.delizarov.mvpcalculator.calculator.domain.Expression
import com.delizarov.mvpcalculator.calculator.viewmodel.CalculatorViewModel
import com.delizarov.mvpcalculator.databinding.FragmentMvvmCalculatorBinding

class MvvmCalculatorFragment: Fragment() {

    private lateinit var viewModel: CalculatorViewModel

    private var _binding: FragmentMvvmCalculatorBinding? = null
    private val binding: FragmentMvvmCalculatorBinding
        get() = requireNotNull(_binding)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this)[CalculatorViewModel::class.java]
        val observer = Observer<CalculationResult> { result -> showResult(result) }
        viewModel.result.observe(viewLifecycleOwner, observer)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentMvvmCalculatorBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calculate.setOnClickListener {
            viewModel.onCalculateClick(binding.expression)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun showResult(result: CalculationResult) {
        when (result) {
            is CalculationResult.Error -> showError(result.error)
            is CalculationResult.Success -> showCalculationResult(result.value)
        }
    }

    private fun showError(error: Throwable) {
        binding.result.text = error.message
    }

    private fun showCalculationResult(value: Int) {
        binding.result.text = value.toString()
    }
}

private val FragmentMvvmCalculatorBinding.expression: Expression
    get() = inputExpression.text.toString()