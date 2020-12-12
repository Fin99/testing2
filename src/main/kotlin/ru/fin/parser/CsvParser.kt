package ru.fin.parser

import ru.fin.function.elementary.impl.LnImpl
import ru.fin.function.elementary.impl.SinImpl
import ru.fin.function.secondary.impl.CosImpl
import ru.fin.function.secondary.impl.CotImpl
import ru.fin.function.secondary.impl.CscImpl
import ru.fin.function.secondary.impl.LogImpl
import ru.fin.function.system.impl.SystemImpl
import java.io.File
import kotlin.math.pow

private val sin = SinImpl()
private val ln = LnImpl()
private val cos = CosImpl(sin)
private val cot = CotImpl(sin)
private val csc = CscImpl(sin)
private val log = LogImpl(ln)
private val system = SystemImpl(sin, cos, cot, csc, log)

private fun sin(x: Double) = sin.sin(x, error!!)
private fun ln(x: Double) = ln.ln(x, error!!)
private fun cos(x: Double) = cos.cos(x, error!!)
private fun cot(x: Double) = cot.cot(x, error!!)
private fun csc(x: Double) = csc.csc(x, error!!)
private fun log(x: Double, base: Double) = log.log(x, base, error!!)
private fun system(x: Double) = system.process(x, error!!)


val error = 10.0.pow(-10)

fun main(args: Array<String>) {

    val filename = args.getOrNull(0) ?: throw IllegalArgumentException()
    val from = args.getOrNull(1)?.toDoubleOrNull()?.takeIf { it.isFinite() } ?: throw IllegalArgumentException()
    val step = args.getOrNull(2)?.toDoubleOrNull()?.takeIf { it.isFinite() } ?: throw IllegalArgumentException()
    val count = args.getOrNull(3)?.toIntOrNull() ?: throw IllegalArgumentException()
    val functionName = args.getOrNull(4) ?: throw IllegalArgumentException()
    val logBase: Double = args.getOrNull(5)?.toDoubleOrNull() ?: 10.0

    val function = when (functionName) {
        "sin" -> { x: Double -> Pair(x, sin(x)) }
        "ln" -> { x: Double -> Pair(x, ln(x)) }
        "cos" -> { x: Double -> Pair(x, cos(x)) }
        "cot" -> { x: Double -> Pair(x, cot(x)) }
        "csc" -> { x: Double -> Pair(x, csc(x)) }
        "log" -> { x: Double -> Pair(x, log(x, logBase)) }
        "system" -> { x: Double -> Pair(x, system(x)) }
        else -> throw IllegalArgumentException("Invalid function")
    }

    val result = StringBuilder()

    generateSequence(from) { it + step }.take(count).map(function)
        .map { "${it.first},${it.second}" }.reduce { acc, s -> "$acc\n$s" }.let {
            File(filename).writeText("x,$functionName\n${it}")
        }

    for (i in 1..count) {
        val run = from.run { function }
        result.append(from.run(function))
    }

}