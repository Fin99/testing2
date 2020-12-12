package ru.fin.function.secondary.impl

import ru.fin.function.elementary.Sin
import ru.fin.function.secondary.Cot
import kotlin.math.abs

class CotImpl(private val elementarySin: Sin): Cot {
    override fun cot(x: Double, error: Double): Double {
        val result = elementarySin.sin(x + Math.PI / 2, error) / elementarySin.sin(x, error)
        return if (result.isNaN() || abs(result) < 0) 0.0 else result
    }
}