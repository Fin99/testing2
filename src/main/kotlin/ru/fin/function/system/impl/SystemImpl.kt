package ru.fin.function.system.impl

import ru.fin.function.elementary.Sin
import ru.fin.function.secondary.Cos
import ru.fin.function.secondary.Cot
import ru.fin.function.secondary.Csc
import ru.fin.function.secondary.Log
import ru.fin.function.system.System
import kotlin.math.E
import kotlin.math.pow

class SystemImpl(
    private val sin: Sin,
    private val cos: Cos,
    private val cot: Cot,
    private val csc: Csc,
    private val log: Log,
) : System {
    var error: Double? = null

    override fun process(x: Double, error: Double): Double {
        this.error = error
        print(Double.POSITIVE_INFINITY - Double.POSITIVE_INFINITY)

        return when {
            x <= 0 -> {
                val firstMultiplier = ((((sin(x) * sin(x)) - sin(x)) + (csc(x) + (cot(x) - csc(x)))) - cot(x))
                val secondMultiplier = (csc(x) + cos(x))
                firstMultiplier * secondMultiplier
            }
            x > 0 -> {
                val numerator =
                    ((((log(x, 10.0) + log(x, 3.0)) * log(x, 3.0)) - (log(x, E) - log(x, 3.0))) - log(x, 2.0))
                val denominator = (log(x, 5.0) + log(x, 5.0)).pow(3.0)
                numerator / denominator
            }
            else -> throw IllegalArgumentException()
        }
    }

    private fun sin(x: Double) = sin.sin(x, error!!)
    private fun cos(x: Double) = cos.cos(x, error!!)
    private fun cot(x: Double) = cot.cot(x, error!!)
    private fun csc(x: Double) = csc.csc(x, error!!)
    private fun log(x: Double, base: Double) = log.log(x, base, error!!)

}