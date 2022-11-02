package com.delizarov.mvpcalculator.calculator.domain

import java.util.Stack

class Calculator {

    private val parser = TermParser()

    fun calculate(expression: Expression): Int {
        var p = 0
        val numbers = Stack<Term.Number>()
        val operands = Stack<Term.Operand>()

        while (p < expression.length) {
            val result = parser.parseTerm(expression, p)
            val term = result.term

            if (term is Term.Number) {
                numbers.push(term)
            }

            if (term is Term.Operand) {
                while (operands.isNotEmpty() && operands.peek().priority >= term.priority) {
                    process(numbers, term)
                    operands.pop()
                }

                operands.push(term)
            }

            p += result.termLength
        }

        while(operands.isNotEmpty()) {
            process(numbers, operands.peek())
            operands.pop()
        }

        return numbers.peek().number
    }

    private fun process(numbers: Stack<Term.Number>, operand: Term.Operand) {
        val right = numbers.pop()
        val left = numbers.pop()

        val result = when (operand) {
            Term.Operand.Sum -> Term.Number(left.number + right.number)
            Term.Operand.Subtract -> Term.Number(left.number - right.number)
            Term.Operand.Multiply -> Term.Number(left.number * right.number)
            Term.Operand.Divide -> Term.Number(left.number / right.number)
        }

        numbers.push(result)
    }
}