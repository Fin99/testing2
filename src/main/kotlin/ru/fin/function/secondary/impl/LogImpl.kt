package ru.fin.function.secondary.impl

import ru.fin.function.elementary.Ln
import ru.fin.function.secondary.Log

class LogImpl(private val elementaryLn: Ln) : Log {
    override fun log(x: Double, base: Double, error: Double): Double {
        return elementaryLn.ln(x, error / 1000) / elementaryLn.ln(base, error / 1000)
    }
}