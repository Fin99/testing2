package ru.fin.system

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import org.mockito.AdditionalMatchers.eq
import org.mockito.Matchers
import org.mockito.Mockito
import ru.fin.function.elementary.Sin
import ru.fin.function.secondary.Cos
import ru.fin.function.secondary.Cot
import ru.fin.function.secondary.Csc
import ru.fin.function.secondary.Log
import ru.fin.function.system.impl.SystemImpl
import java.lang.Math.E

class SystemTest {
    private val elementarySin = Mockito.mock(Sin::class.java)
    private val elementaryCos = Mockito.mock(Cos::class.java)
    private val elementaryCot = Mockito.mock(Cot::class.java)
    private val elementaryCsc = Mockito.mock(Csc::class.java)
    private val elementaryLog = Mockito.mock(Log::class.java)

    private val system = SystemImpl(elementarySin, elementaryCos, elementaryCot, elementaryCsc, elementaryLog)

    @ParameterizedTest
    @CsvFileSource(resources = ["/system/system.csv"], numLinesToSkip = 1)
    fun logTest(
        x: Double,
        y: Double,
        sin: Double,
        cos: Double,
        cot: Double,
        csc: Double,
        ln: Double,
        log2: Double,
        log3: Double,
        log5: Double,
        log10: Double,
        error: Double
    ) {
        Mockito.`when`(elementarySin.sin(eq(x, error), Matchers.anyDouble())).thenReturn(sin)
        Mockito.`when`(elementaryCos.cos(eq(x, error), Matchers.anyDouble())).thenReturn(cos)
        Mockito.`when`(elementaryCot.cot(eq(x, error), Matchers.anyDouble())).thenReturn(cot)
        Mockito.`when`(elementaryCsc.csc(eq(x, error), Matchers.anyDouble())).thenReturn(csc)
        Mockito.`when`(elementaryLog.log(eq(x, error), eq(E, error), Matchers.anyDouble())).thenReturn(ln)
        Mockito.`when`(elementaryLog.log(eq(x, error), eq(2.0, error), Matchers.anyDouble())).thenReturn(log2)
        Mockito.`when`(elementaryLog.log(eq(x, error), eq(3.0, error), Matchers.anyDouble())).thenReturn(log3)
        Mockito.`when`(elementaryLog.log(eq(x, error), eq(5.0, error), Matchers.anyDouble())).thenReturn(log5)
        Mockito.`when`(elementaryLog.log(eq(x, error), eq(10.0, error), Matchers.anyDouble())).thenReturn(log10)
        Assertions.assertEquals(y, system.process(x, error), error)
    }
}