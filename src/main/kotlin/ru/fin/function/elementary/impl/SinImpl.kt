package ru.fin.function.elementary.impl

import ru.fin.function.elementary.Sin
import kotlin.math.abs

class SinImpl : Sin {
    override fun sin(x: Double, error: Double): Double {
        if (!x.isFinite() || !error.isFinite()) throw IllegalArgumentException()

        var sin = 0.0
        var step = x
        var i = 1

        while (abs(step) > error) {
            sin += step
            step *= -x * x / (2 * i * (2 * i + 1))
            i++
        }

        return sin
    }
}