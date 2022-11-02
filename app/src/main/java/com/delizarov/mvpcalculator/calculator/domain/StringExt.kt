package com.delizarov.mvpcalculator.calculator.domain

val Char.isMathOperator: Boolean
    get() = this == '+'
        || this == '-'
        || this == '*'
        || this == '/'