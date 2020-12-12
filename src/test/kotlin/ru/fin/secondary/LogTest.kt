package ru.fin.secondary

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import org.mockito.AdditionalMatchers
import org.mockito.Matchers
import org.mockito.Mockito
import ru.fin.function.elementary.Ln
import ru.fin.function.secondary.impl.LogImpl

class LogTest {
    private val elementaryLn = Mockito.mock(Ln::class.java)

    @ParameterizedTest
    @CsvFileSource(resources = ["/secondary/log.csv"], numLinesToSkip = 1)
    fun logTest(
        x: Double,
        base: Double,
        xLog: Double,
        baseLog: Double,
        log: Double,
        error: Double
    ) {
        Mockito.`when`(elementaryLn.ln(AdditionalMatchers.eq(x, error), Matchers.anyDouble())).thenReturn(xLog)
        Mockito.`when`(elementaryLn.ln(AdditionalMatchers.eq(base, error), Matchers.anyDouble())).thenReturn(baseLog)
        Assertions.assertEquals(log, LogImpl(elementaryLn).log(x, base, error), error)
    }
}