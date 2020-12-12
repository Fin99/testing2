package ru.fin.function.secondary.impl

import ru.fin.function.elementary.Sin
import ru.fin.function.secondary.Cos
import kotlin.math.abs

class CosImpl(private val elementarySin: Sin): Cos {
    override fun cos(x: Double, error: Double): Double {
        val result = elementarySin.sin(x + Math.PI / 2, error)
        return if (result.isNaN() || abs(result) < 0) 0.0 else result
    }
}