package ru.fin.function.secondary.impl

import ru.fin.function.elementary.Sin
import ru.fin.function.secondary.Csc
import kotlin.math.abs

class CscImpl(private val elementarySin: Sin) : Csc {
    override fun csc(x: Double, error: Double): Double {
        val result = 1.0 / elementarySin.sin(x, error)
        return if (result.isNaN() || abs(result) < 0) 0.0 else result
    }
}