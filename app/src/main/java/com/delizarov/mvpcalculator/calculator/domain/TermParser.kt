package com.delizarov.mvpcalculator.calculator.domain

class TermParser {

    fun parseTerm(expression: Expression, startPos: Int): ParseResult {
        return when {
            expression[startPos].isDigit() -> expatNumber(expression, startPos)
            expression[startPos].isMathOperator -> expatOperand(expression, startPos)
            else -> throw TermParseException(expression, startPos)
        }
    }

    private fun expatNumber(expression: Expression, startPos: Int): ParseResult {
        var p = startPos
        val number = buildString {
            append(expression[p++])

            while (p < expression.length && expression[p].isDigit()) {
                append(expression[p++])
            }
        }.toInt()

        return ParseResult(
            termLength = p - startPos,
            term = Term.Number(number)
        )
    }

    private fun expatOperand(expression: Expression, startPos: Int): ParseResult {
        val term = Term.Operand.createFromChar(expression[startPos]) ?: throw TermParseException(expression, startPos)

        return ParseResult(
            termLength = 1,
            term = term,
        )
    }

    class ParseResult(
        val termLength: Int,
        val term: Term,
    )
}