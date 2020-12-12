package ru.fin.function.elementary.impl

import ru.fin.function.elementary.Ln
import kotlin.math.abs
import kotlin.math.pow

class LnImpl : Ln {
    override fun ln(x: Double, error: Double): Double {
        if (x <= 0 || x <= error || x.isNaN() || !error.isFinite()) throw IllegalArgumentException()
        
        if (x == Double.POSITIVE_INFINITY) return Double.POSITIVE_INFINITY
        
        if (x < 1) {
            val replace = x - 1
            var step = replace
            var ln = replace
            var i = 2
            
            while (abs(step) >= error) {
                step *= -replace
                ln += step / i
                i++
            }
            
            return ln
        } else {
            val replace = x / (x - 1.0)
            var step = replace
            var ln = 0.0
            var i = 1
            
            while (abs(step) > error) {
                step = 1.0 / (i * replace.pow(i))
                ln += step
                i++
            }
            
            return ln
        }
    }
}