package com.delizarov.mvpcalculator.calculator.domain

import java.lang.IllegalArgumentException

class TermParseException(
    expression: Expression,
    failedPosition: Int
): IllegalArgumentException("Failed to parse '$expression' at $failedPosition char")